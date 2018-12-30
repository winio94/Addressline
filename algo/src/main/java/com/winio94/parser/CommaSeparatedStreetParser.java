package com.winio94.parser;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommaSeparatedStreetParser extends StreetParser implements ConditionalParser {

    private static final String COMMA = ",";
    private static final Pattern START_FROM_DIGIT = Pattern.compile("^(\\d)(.)*");

    @Override
    protected List<String> split(String street) {
        return Arrays.asList(street.split(COMMA));
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

    @Override
    public boolean shouldParse(String input) {
        return input.contains(COMMA);
    }
}
