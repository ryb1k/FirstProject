package com.example.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.test3.BuildConfig.RELATIVE_CATEGORY_URL;
import static com.example.test3.BuildConfig.apiVariable;
import static com.example.test3.BuildConfig.key;

/**
 * Created by Денис on 26.07.2017.
 */

public class CategoryApi extends BaseApi { // TODO: 02.08.2017 update like ProductApi

    public MainActivity mainActivity;

    public CategoryApi(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void userLogin() throws JSONException {

        RequestParams params = new RequestParams();
        params.put(apiVariable, key);

        BaseApi.get(RELATIVE_CATEGORY_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List <Category> categories = new ArrayList<>();
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    String result = response.toString();
                    JsonElement element = parser.parse(result);
                    JsonObject object = element.getAsJsonObject();
                    JsonObject data = object.getAsJsonObject("data");
                    JsonArray categoriesArray = data.getAsJsonArray("categories");
                    for (int categoryIndex = 0; categoryIndex < categoriesArray.size(); categoryIndex++) {
                        JsonElement categoryIndexElement = categoriesArray.get(categoryIndex);
                        JsonObject categoryObject = categoryIndexElement.getAsJsonObject();
                        Category category = gson.fromJson(categoryObject, Category.class);
                        categories.add(category);
                    }
                    mainActivity.onDownloadSuccessCategory(categories);
                } catch (Exception e) {
                    int abc=123;
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                try {
                    JSONObject firstEvent = timeline.getJSONObject(0);
                    String tweetText = firstEvent.getString("success");
                    //tview.setText((tweetText));
                } catch (JSONException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println(errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
            }
        });
    }
}
