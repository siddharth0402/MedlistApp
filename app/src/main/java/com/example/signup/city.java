package com.example.signup;

import android.content.Context;
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

public class city extends AppCompatActivity {
    RecyclerView recyclerView;
    List<StractCity> myList;
    CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Toolbar toolbar = findViewById(R.id.search_toolbarC);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        displayItem();


    }

    private void displayItem() {
        recyclerView = (RecyclerView) findViewById(R.id.listCity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CityDatabase mDBHELPER = new CityDatabase(city.this, "Hospital");
        File database = getApplicationContext().getDatabasePath(CityDatabase.DBNAME);
        if (!database.exists()) {
            mDBHELPER.getReadableDatabase();
            if (!database.exists()) {
                mDBHELPER.getReadableDatabase();
                if (!copydatabase(city.this)) {
                    return;
                }
            }
        }
        myList = mDBHELPER.GetCiity();
        cityAdapter=new CityAdapter(myList,this);
        cityAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(cityAdapter);

    }

//    private void setAdapter(RecyclerView listHospital) {
//
//    }

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
                cityAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}
