package com.example.test3.Api;

import com.example.test3.Listeners.BaseApiListener;
import com.example.test3.BaseClasses.Category;
import com.example.test3.Listeners.CategoryApiListener;
import com.example.test3.JsonHelper;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.example.test3.BuildConfig.RELATIVE_CATEGORY_URL;


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
