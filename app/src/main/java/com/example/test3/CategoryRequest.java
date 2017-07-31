package com.example.test3;

import com.google.gson.Gson;
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

/**
 * Created by Денис on 26.07.2017.
 */

public class CategoryRequest
{
    public MainActivity mainActivity;

    public CategoryRequest(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void userLogin() throws JSONException {

        RequestParams params = new RequestParams();
        params.put("appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");

        MainActivity.BaseRestClient.get("api/common/category/list", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<MyData>dataList = new ArrayList<>();
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    String result = response.toString();
                    JsonObject root = parser.parse(result).getAsJsonObject().getAsJsonObject("data").getAsJsonArray("categories").get(0).getAsJsonObject();
                    MyData category = gson.fromJson(root, MyData.class);
                    dataList.add(category);
                    mainActivity.onDownloadSuccessCategory(dataList);
                    //gridLayoutManager = new GridLayoutManager(MainActivity.this,2);

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
