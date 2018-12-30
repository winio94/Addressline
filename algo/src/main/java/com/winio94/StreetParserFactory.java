package com.winio94;

import com.winio94.parser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreetParserFactory {

    private final List<ConditionalParser> parsers;

    public StreetParserFactory() {
        this.parsers = new ArrayList<>();
        this.parsers.add(new CommaSeparatedStreetParser());
        this.parsers.add(new StreetWithHouseNumberPrefixParser());
    }

    public Parser get(String input) {
        Optional<ConditionalParser> interestedParser = parsers.stream()
                .filter(p -> p.shouldParse(input))
                .findFirst();

        return interestedParser.isPresent() ? interestedParser.get() : new DefaultStreetParser();
    }
}
