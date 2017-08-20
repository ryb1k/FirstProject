package com.example.test3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.test3.BuildConfig.SERVER_URL;
import static com.example.test3.BuildConfig.key;

/**
 * Created by Денис on 07.08.2017.
 */

public class BaseApi {

    private static final String BASE_URL = SERVER_URL;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void get(String url, RequestParams params, final BaseApiListener responseHandler) {
        params.put("appKey", key);
        client.get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Object data = response.get("data");
                    JSONObject meta = response.getJSONObject("meta");
                    responseHandler.onSuccess(data);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(responseString);
            }
        });

    }


    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
