package com.example.test3;

import com.google.gson.JsonArray;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.test3.BuildConfig.RELATIVE_CATEGORY_URL;
import static com.example.test3.BuildConfig.key;


/**
 * Created by Денис on 26.07.2017.
 */

public class CategoryApi extends BaseApi {

    public void loadCategories(final CategoryApiListener categoryApiListener) throws JSONException {

        final BaseApiListener baseApiListener = this;
        RequestParams params = new RequestParams();
        params.put("appKey", key);

        get(RELATIVE_CATEGORY_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JsonHelper jsonHelper = new JsonHelper();
                    JsonArray array = baseApiListener.onParseCategoryResponse(response);
                    List<Category> categories = jsonHelper.parseArrayFromJson(Category.class,array);
                    categoryApiListener.onCategoryLoaded(categories);
                } catch (Exception e) {
                    System.out.println(e);
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
