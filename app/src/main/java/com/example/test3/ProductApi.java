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

import static com.example.test3.BuildConfig.RELATIVE_PRODUCT_URL;

import static com.example.test3.BuildConfig.apiCategoryIdVariable;
import static com.example.test3.BuildConfig.apiKeyVariable;
import static com.example.test3.BuildConfig.key;

/**
 * Created by Денис on 26.07.2017.
 */

public class ProductApi extends BaseApi {

    private MainActivity mainActivity; // TODO: 02.08.2017 remove

    public ProductApi(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void loadProducts() throws JSONException { // TODO: 02.08.2017 LoadProducts(int categoryId, ProductListener listener)

        RequestParams params = new RequestParams();
        params.put(apiKeyVariable, key);
        //params.put(apiCategoryIdVariable,categoryId);

        BaseApi.get(RELATIVE_PRODUCT_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<Product> products = new ArrayList<>();
                    JsonHelper jsonHelper = new JsonHelper();
                    jsonHelper.parseProductList(products, response);
                    mainActivity.onDownloadSuccessProduct(products);
                    // TODO: 02.08.2017 call listener.onProductsLoaded(products)
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