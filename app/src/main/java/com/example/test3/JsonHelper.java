package com.example.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class JsonHelper {

    public void parseCategoryList(List<Category> categories, JSONObject response) {
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
    }

    public void parseProductList(List<Product> products, JSONObject response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String result = response.toString();
        JsonElement element = parser.parse(result);
        JsonObject object = element.getAsJsonObject();
        JsonArray productsArray = object.getAsJsonArray("data");
        for (int productIndex = 0; productIndex < productsArray.size(); productIndex++) {
            JsonElement productIndexElement = productsArray.get(productIndex);
            JsonObject productObject = productIndexElement.getAsJsonObject();
            Product product = gson.fromJson(productObject, Product.class);
            products.add(product);
        }
    }
}
