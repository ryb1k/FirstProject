package com.example.test3;

import com.google.gson.JsonArray;

import org.json.JSONObject;

/**
 * Created by Денис on 12.08.2017.
 */

public interface BaseApiListener {
    JsonArray onParseCategoryResponse(JSONObject response);
    JsonArray onParseProductResponse(JSONObject response);
}
