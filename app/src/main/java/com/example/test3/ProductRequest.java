package com.example.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

public class ProductRequest { // TODO: 02.08.2017 rename to ProductApi, extend from BaseApi

    private MainActivity mainActivity; // TODO: 02.08.2017 remove

    public ProductRequest(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void userLogin() throws JSONException { // TODO: 02.08.2017 LoadProducts(int categoryId, ProductListener listener)

        RequestParams params = new RequestParams();
        params.put("appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m"); // TODO: 02.08.2017 extract to gradle

        MainActivity.BaseRestClient.get("api/common/product/list", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<MyData1> dataList1 = new ArrayList<>(); // TODO: 02.08.2017 products
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    String result = response.toString();
                    JsonArray root = parser.parse(result).getAsJsonObject().getAsJsonArray("data"); // TODO: 02.08.2017 shorten this
                    for (int i=0;i<root.size();i++) {// TODO: 02.08.2017 update formatting
                        // TODO: 02.08.2017 rename i to index or productIndex
                        JsonObject tile = root.get(i).getAsJsonObject();
                        MyData1 product = gson.fromJson(tile, MyData1.class);
                        dataList1.add(product);
                    }// TODO: 02.08.2017 extract to template method JsonHelper.parseItemList
                    mainActivity.onDownloadSuccessProduct(dataList1);
                    // TODO: 02.08.2017 call listener.onProductsLoaded(products)
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