package com.example.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class JsonHelper {

    public <T> List<T> parseArrayFromJson(Class<T> classOfT, JsonArray jsonArray) throws JsonSyntaxException {

        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        for (int index = 0; index < jsonArray.size(); index++) {
            JsonElement jsonElement = jsonArray.get(index);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            T category = gson.fromJson(jsonObject, classOfT);
            list.add(category);
        }
        return list;
    }
}
