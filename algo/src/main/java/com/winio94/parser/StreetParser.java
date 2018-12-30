package com.winio94.parser;

import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StreetParser implements Parser {

    @Override
    public JSONObject parse(String input) {
        List<String> chunks = split(input);
        List<String> trimmedChunks = trimChunks(chunks);
        return buildJson(trimmedChunks);
    }

    private List<String> trimChunks(List<String> chunks) {
        return chunks.stream().map(c -> c = c.trim()).collect(Collectors.toList());
    }

    protected abstract List<String> split(String street);

    protected abstract JSONObject buildJson(List<String> chunks);
}
