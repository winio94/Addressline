package com.winio94;

import org.json.JSONObject;

public class App {

    public JSONObject convert(String street) {
        StreetParserFactory parserFactory = new StreetParserFactory();
        return parserFactory.get(street).parse(street);
    }
}