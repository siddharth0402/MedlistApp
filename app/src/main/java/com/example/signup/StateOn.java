package com.example.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup.Database.CityDatabase;
import com.example.signup.ModelH.StractCity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class StateOn extends AppCompatActivity {
    RecyclerView recyclerView;
    List<StractCity> myList;
    S2Cadapter s2Cadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_on);
        Toolbar toolbar = findViewById(R.id.search_toolbarSO);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String abc= intent.getStringExtra("City");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        displayItem(abc);

    }
    private void displayItem(String abcd) {
        recyclerView = (RecyclerView) findViewById(R.id.listCityfromState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CityDatabase mDBHELPER = new CityDatabase(StateOn.this, "Hospital");
        File database = getApplicationContext().getDatabasePath(CityDatabase.DBNAME);
        if (!database.exists()) {
            mDBHELPER.getReadableDatabase();
            if (!database.exists()) {
                mDBHELPER.getReadableDatabase();
                if (!copydatabase(StateOn.this)) {
                    return;
                }
            }
        }
        myList = mDBHELPER.GetCityFromState(abcd);
        s2Cadapter=new S2Cadapter(myList,this);
        s2Cadapter.notifyDataSetChanged();
        recyclerView.setAdapter(s2Cadapter);

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
               // S2Cadapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
    public Boolean copydatabase(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(CityDatabase.DBNAME);
            String OutFileName = CityDatabase.DBLOCATION + CityDatabase.DBNAME;
            File f = new File(OutFileName);
            f.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(OutFileName);

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}