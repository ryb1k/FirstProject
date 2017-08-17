package com.example.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class JsonHelper {

    public static <T> List<T> parseArrayFromJson(Class<T> classOfT, JSONArray jsonArray) throws JsonSyntaxException {

        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        for (int index = 0; index < jsonArray.length(); index++) {
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                T category = gson.fromJson(String.valueOf(jsonObject), classOfT);
                list.add(category);
            } catch (JSONException e) {
            }
          }
        return list;
    }
}
