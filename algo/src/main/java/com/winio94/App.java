package com.winio94;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    private static final Pattern LETTERS_OR_DIGITS = Pattern.compile("[\\D]+|([\\d]\\s*(\\D(\\s|$))?)+");
    private static final Pattern START_FROM_DIGIT = Pattern.compile("^(\\d)(.)*");
    private static final Pattern HOUSE_NUMBER_WITH_PREFIX = Pattern.compile("(.)*\\s?No\\s?\\d*(.)*");
    private static final String COMMA = ",";
    private static final String HOUSE_NUMBER_PREFIX = "No ";

    public JSONObject convert(String street) {
        List<String> chunks = new LinkedList<>();
        if (street.contains(COMMA)) {
            chunks = Arrays.asList(street.split(COMMA));
        } else if (HOUSE_NUMBER_WITH_PREFIX.matcher(street).matches()) {
            chunks = Arrays.asList(street.split(HOUSE_NUMBER_PREFIX));
        } else {
            Matcher matcher = LETTERS_OR_DIGITS.matcher(street);
            while (matcher.find()) {
                chunks.add(matcher.group());
            }
        }
        chunks = chunks.stream().map(c -> c = c.trim()).collect(Collectors.toList());

        JSONObject result = new JSONObject();
        for (String s : chunks) {
            if (START_FROM_DIGIT.matcher(s).matches()) {
                String houseNumberPrefix = "";
                if (HOUSE_NUMBER_WITH_PREFIX.matcher(street).matches()) {
                    houseNumberPrefix = HOUSE_NUMBER_PREFIX;
                }
                result.put("houseNumber", houseNumberPrefix + s.trim());
            } else {
                result.put("street", s.trim());
            }
        }
        return result;
    }
}