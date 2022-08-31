package com.example.signup;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class beds_home extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private bedsAdapter mBedsAdapter;
    private ArrayList<bedsItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beds_home);
        Toolbar toolbar = findViewById(R.id.search_toolbarB);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void parseJSON() {
        String url = "https://script.google.com/macros/s/AKfycbyubWCEQ2YdtZBB7S2zG58WyeYlHWZUFRne2nYGfqVfgMj97W85A8iApvqZC-krUMXu/exec";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            JSONObject data = response.getJSONObject("data");
                            JSONArray jsonArray = response.getJSONArray("data");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject regionals = jsonArray.getJSONObject(i);

                                String stateName = regionals.getString("state");
                                int ruralHospitals = regionals.getInt("ruralHospitals");
                                int ruralBeds = regionals.getInt("ruralBeds");
                                String urbanHospitals = regionals.getString("urbanHospitals");
                                int urbanBeds = regionals.getInt("urbanBeds");
                                int totalHospitals = regionals.getInt("totalHospitals");

                                mExampleList.add(new bedsItem(stateName, ruralHospitals, ruralBeds,urbanHospitals, urbanBeds,  totalHospitals));
                            }

                            mBedsAdapter = new bedsAdapter(beds_home.this, mExampleList);
                            mRecyclerView.setAdapter(mBedsAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mBedsAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}