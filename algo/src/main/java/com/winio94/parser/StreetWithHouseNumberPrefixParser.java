package com.winio94.parser;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class StreetWithHouseNumberPrefixParser extends StreetParser implements ConditionalParser {

    private static final Pattern HOUSE_NUMBER_WITH_PREFIX = Pattern.compile("(.)*\\s?No\\s?\\d*(.)*");
    private static final String HOUSE_NUMBER_PREFIX = "No ";

    public StreetWithHouseNumberPrefixParser() {
        super(houseNumberPrefixModifier());
    }

    @Override
    protected List<String> split(String street) {
        return Arrays.asList(street.split(HOUSE_NUMBER_PREFIX));
    }

    @Override
    public boolean shouldParse(String input) {
        return HOUSE_NUMBER_WITH_PREFIX.matcher(input).matches();
    }

    private static UnaryOperator<JSONObject> houseNumberPrefixModifier() {
        return jsonObject -> {
            String houseNumber = jsonObject.getString("houseNumber");
            jsonObject.put("houseNumber", StreetWithHouseNumberPrefixParser.HOUSE_NUMBER_PREFIX + houseNumber);
            return jsonObject;
        };
    }
}
