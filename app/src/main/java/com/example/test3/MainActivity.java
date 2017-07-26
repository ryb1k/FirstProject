package com.example.test3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> dataList;
    //private List<MyData1> dataList1;
    private CustomAdapter1 adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            CategoryRequest categoryRequest = new CategoryRequest();
            categoryRequest.userLogin();
        } catch (Exception e) {
            System.out.println(e);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        dataList = new ArrayList<>();


            //recyclerView.setVisibility(View.INVISIBLE);
    }

    public void onClick(View view) {
        recyclerView.setVisibility(View.INVISIBLE);
        try {
            ProductRequest productRequest = new ProductRequest();
            productRequest.userLogin();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("onclick");
        recyclerView.setVisibility(View.VISIBLE);
    }



    public static class BaseRestClient
    {
        private static final String BASE_URL = "http://82.146.53.185:8101/";

        private static AsyncHttpClient client = new AsyncHttpClient();

        public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }

        public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
    }

    public class CategoryRequest
    {
        public void userLogin() throws JSONException {

            RequestParams params = new RequestParams();
            params.put("appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");

            BaseRestClient.get("api/common/category/list", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        String result = response.toString();
                        JsonObject root = parser.parse(result).getAsJsonObject().getAsJsonObject("data").getAsJsonArray("categories").get(0).getAsJsonObject();
                        MyData category = gson.fromJson(root, MyData.class);
                        dataList.add(category);
                        //gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));

                        adapter = new CustomAdapter(MainActivity.this,dataList);
                        recyclerView.setAdapter(adapter);
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

    public class ProductRequest
    {
        public void userLogin() throws JSONException {

            RequestParams params = new RequestParams();
            params.put("appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");

            BaseRestClient.get("api/common/product/list", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        List<MyData1> dataList1 = new ArrayList<>();
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        String result = response.toString();
                        JsonArray root = parser.parse(result).getAsJsonObject().getAsJsonArray("data");
                        for (int i=0;i<root.size();i++) {
                            JsonObject tile = root.get(i).getAsJsonObject();
                            MyData1 product = gson.fromJson(tile, MyData1.class);
                            dataList1.add(product);
                        }
                        //gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));

                        adapter1 = new CustomAdapter1(MainActivity.this,dataList1,ProductRequest.this);
                        recyclerView.setAdapter(adapter1);
                        setTitle(dataList.get(0).getTitle());
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
}
