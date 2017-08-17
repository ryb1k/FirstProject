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

    public void loadCategories(final CategoryApiListener categoryApiListener) {
        RequestParams params = new RequestParams();
        get(RELATIVE_CATEGORY_URL, params, new BaseApiListener() {
            @Override
            public void onSuccess(Object data) {
                try {
                    JSONObject object = (JSONObject) data;
                    JSONArray array = object.getJSONArray("categories");
                    List<Category> categories = JsonHelper.parseArrayFromJson(Category.class, array);
                    categoryApiListener.onCategoryLoaded(categories);
                } catch (Exception e) {
                    categoryApiListener.onFailure(e.toString());
                }
            }

            @Override
            public void onFailure(String error) {
                categoryApiListener.onFailure(error);
            }

        });
    }
}
