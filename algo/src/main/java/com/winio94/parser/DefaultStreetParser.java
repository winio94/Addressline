package com.winio94.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultStreetParser extends StreetParser {

    private static final Pattern LETTERS_OR_DIGITS = Pattern.compile("[\\D]+|([\\d]\\s*(\\D(\\s|$))?)+");

    public DefaultStreetParser() {
        super(UnaryOperator.identity());
    }

    @Override
    protected List<String> split(String street) {
        List<String> chunks = new LinkedList<>();
        Matcher matcher = LETTERS_OR_DIGITS.matcher(street);
        while (matcher.find()) {
            chunks.add(matcher.group());
        }
        return chunks;
    }
}
