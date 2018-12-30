package com.winio94.parser;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StreetWithHouseNumberPrefixParser extends StreetParser implements ConditionalParser{

    public static final Pattern HOUSE_NUMBER_WITH_PREFIX = Pattern.compile("(.)*\\s?No\\s?\\d*(.)*");
    private static final String HOUSE_NUMBER_PREFIX = "No ";
    private static final Pattern START_FROM_DIGIT = Pattern.compile("^(\\d)(.)*");

    @Override
    protected List<String> split(String street) {
        return Arrays.asList(street.split(HOUSE_NUMBER_PREFIX));
    }

    @Override
    protected JSONObject buildJson(List<String> chunks) {
        JSONObject result = new JSONObject();
        for (String s : chunks) {
            if (START_FROM_DIGIT.matcher(s).matches()) {
                result.put("houseNumber", HOUSE_NUMBER_PREFIX + s.trim());
            } else {
                result.put("street", s.trim());
            }
        }
        return result;
    }

    @Override
    public boolean shouldParse(String input) {
        return HOUSE_NUMBER_WITH_PREFIX.matcher(input).matches();
    }
}
