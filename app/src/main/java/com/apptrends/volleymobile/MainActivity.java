package com.apptrends.volleymobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout mRefresh;
    ArrayList<ModelClass> dataList;
    Adapter madapter;
    ProgressDialog mProgressDialog;
    RequestQueue requestQueue;
    //String Url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=tiger";
    //String Url = "https://volleyandroid.000webhostapp.com/";
    String Url = "https://volleyandroid.000webhostapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("loading");

        mRefresh = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        dataList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        LoadData();

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //dataList.clear();
                LoadData();
            }
        });

    }

    private void LoadData() {
        mProgressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataList.clear();
                        try {
                            JSONArray jsonArray = response.getJSONArray("records");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String name = hit.getString("Name");
                                String tags = hit.getString("Email");
                                String type = hit.getString("Pass");
                                String image = hit.getString("Image");
                                dataList.add(new ModelClass(name,tags,type,image));
                            }
                            madapter = new Adapter(MainActivity.this, dataList);
                            recyclerView.setAdapter(madapter);
                            mRefresh.setRefreshing(false);
                            mProgressDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //requestQueue.add(request);

        MySingleton.getInstance(MainActivity.this).addToRequestQue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addItem)
        {
            startActivity(new Intent(MainActivity.this,AddProductsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}