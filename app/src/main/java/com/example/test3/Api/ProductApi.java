package com.example.test3.Api;

import com.example.test3.Listeners.BaseApiListener;
import com.example.test3.JsonHelper;
import com.example.test3.BaseClasses.Product;
import com.example.test3.Listeners.ProductApiListener;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.List;

import static com.example.test3.BuildConfig.RELATIVE_PRODUCT_URL;

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