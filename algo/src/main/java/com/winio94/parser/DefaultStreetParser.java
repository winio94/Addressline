package com.winio94.parser;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultStreetParser extends StreetParser {

    private static final Pattern LETTERS_OR_DIGITS = Pattern.compile("[\\D]+|([\\d]\\s*(\\D(\\s|$))?)+");
    private static final Pattern START_FROM_DIGIT = Pattern.compile("^(\\d)(.)*");

    @Override
    protected List<String> split(String street) {
        List<String> chunks = new LinkedList<>();
        Matcher matcher = LETTERS_OR_DIGITS.matcher(street);
        while (matcher.find()) {
            chunks.add(matcher.group());
        }
        return chunks;
    }

    @Override
    protected JSONObject buildJson(List<String> chunks) {
        JSONObject result = new JSONObject();
        for (String s : chunks) {
            if (START_FROM_DIGIT.matcher(s).matches()) {
                result.put("houseNumber", s.trim());
            } else {
                result.put("street", s.trim());
            }
        }
        return result;
    }
}
