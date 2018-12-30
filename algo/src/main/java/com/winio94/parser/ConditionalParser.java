package com.winio94.parser;

public interface ConditionalParser extends Parser {
    boolean shouldParse(String input);
}
