package com.example.test3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.test3.BuildConfig.SERVER_URL;

/**
 * Created by Денис on 07.08.2017.
 */

public class BaseApi implements BaseApiListener {

    private static final String BASE_URL = SERVER_URL;  

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    @Override
    public JsonArray onParseCategoryResponse(JSONObject response) {
        JsonParser parser = new JsonParser();
        String result = response.toString();
        JsonElement element = parser.parse(result);
        JsonObject object = element.getAsJsonObject();
        JsonObject data = object.getAsJsonObject("data");
        JsonArray categoriesArray = data.getAsJsonArray("categories");
        return categoriesArray;
    }

    @Override
    public JsonArray onParseProductResponse(JSONObject response) {
        JsonParser parser = new JsonParser();
        String result = response.toString();
        JsonElement element = parser.parse(result);
        JsonObject object = element.getAsJsonObject();
        JsonArray productsArray = object.getAsJsonArray("data");
        return productsArray;
    }
}
