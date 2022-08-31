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

import com.example.signup.Database.C2H;
import com.example.signup.Database.CityDatabase;
import com.example.signup.ModelH.C2HM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CityToHospital extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<C2HM> myList;
    C2Hadapter c2Hadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_to_hospital);
        Toolbar toolbar = findViewById(R.id.search_toolbarCH);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //textView = findViewById(R.id.kc);
       // textView.setText(getIntent().getStringExtra("City"));
        Intent intent = getIntent();
        String abc = intent.getStringExtra("City");
        displayItem(abc);


    }
    private void displayItem(String abc) {
        recyclerView = (RecyclerView) findViewById(R.id.liistCity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        C2H mDBHELPER = new C2H(CityToHospital.this, "Hospital");
        File database = getApplicationContext().getDatabasePath(C2H.DBNAME);
        if (!database.exists()) {
            mDBHELPER.getReadableDatabase();
            if (!database.exists()) {
                mDBHELPER.getReadableDatabase();
                if (!copydatabase(CityToHospital.this)) {
                    return;
                }
            }
        }
        myList = mDBHELPER.Gethosc(abc);
        c2Hadapter=new C2Hadapter(myList,this);
        c2Hadapter.notifyDataSetChanged();
        recyclerView.setAdapter(c2Hadapter);

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
                c2Hadapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}



