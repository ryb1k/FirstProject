package com.example.test3;

import com.google.gson.JsonArray;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.test3.BuildConfig.RELATIVE_PRODUCT_URL;


import static com.example.test3.BuildConfig.key;

/**
 * Created by Денис on 26.07.2017.
 */

public class ProductApi extends BaseApi {

    public void loadProducts(int categoryId, int offset, final ProductApiListener productApiListener) {
        RequestParams params = new RequestParams();
        params.put("categoryId", categoryId);
        params.put("offset", offset);
        get(RELATIVE_PRODUCT_URL, params, new BaseApiListener() {
            @Override
            public void onSuccess(Object data) {
                JSONArray object = (JSONArray) data;
                List<Product> products = JsonHelper.parseArrayFromJson(Product.class, object);
                productApiListener.onProductsLoaded(products);
            }

            @Override
            public void onFailure(String error) {
                productApiListener.onFailure(error);
            }
        });

    }
}