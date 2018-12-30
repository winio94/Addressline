package com.winio94.parser;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class CommaSeparatedStreetParser extends StreetParser implements ConditionalParser {

    private static final String COMMA = ",";

    public CommaSeparatedStreetParser() {
        super(UnaryOperator.identity());
    }

    @Override
    protected List<String> split(String street) {
        return Arrays.asList(street.split(COMMA));
    }

    @Override
    public boolean shouldParse(String input) {
        return input.contains(COMMA);
    }
}
