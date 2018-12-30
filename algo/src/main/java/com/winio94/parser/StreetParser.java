package com.winio94.parser;

import org.json.JSONObject;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class StreetParser implements Parser {
    private static final Pattern START_FROM_DIGIT = Pattern.compile("^(\\d)(.)*");
    private final UnaryOperator<JSONObject> postProcessor;

    protected StreetParser(UnaryOperator<JSONObject> postProcessor) {
        this.postProcessor = postProcessor;
    }

    @Override
    public JSONObject parse(String input) {
        List<String> chunks = split(input);
        List<String> trimmedChunks = trimChunks(chunks);
        JSONObject jsonObject = buildJson(trimmedChunks);
        return postProcessResult(jsonObject);
    }

    private List<String> trimChunks(List<String> chunks) {
        return chunks.stream().map(c -> c = c.trim()).collect(Collectors.toList());
    }

    private JSONObject buildJson(List<String> chunks) {
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

    private JSONObject postProcessResult(JSONObject jsonObject) {
        return postProcessor.apply(jsonObject);
    }

    protected abstract List<String> split(String street);
}
