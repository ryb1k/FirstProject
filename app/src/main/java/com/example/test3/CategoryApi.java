package com.example.test3;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.test3.BuildConfig.RELATIVE_CATEGORY_URL;
import static com.example.test3.BuildConfig.apiKeyVariable;
import static com.example.test3.BuildConfig.key;


/**
 * Created by Денис on 26.07.2017.
 */

public class CategoryApi extends BaseApi { // TODO: 02.08.2017 update like ProductApi

   public void loadCategories(final OnCategoriesLoadListener onCategoriesLoadListener) throws JSONException {

        RequestParams params = new RequestParams();
        params.put(apiKeyVariable, key);

        BaseApi.get(RELATIVE_CATEGORY_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List <Category> categories = new ArrayList<>();
                    JsonHelper jsonHelper = new JsonHelper();
                    jsonHelper.parseCategoryList(categories,response);
                    onCategoriesLoadListener.onCategoryLoaded(categories);
                    //mainActivity.onDownloadSuccessCategory(categories);
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
