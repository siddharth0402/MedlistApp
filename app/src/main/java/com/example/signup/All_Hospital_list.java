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


import com.example.signup.Database.DatabaseHelper;
import com.example.signup.ModelH.StractHospital;
import com.example.signup.adapter.HospitalAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class All_Hospital_list extends AppCompatActivity {
    // ArrayList<StractHospital> arrayList = new ArrayList<StractHospital>();
    //ArrayAdapter adapter;
    RecyclerView recyclerView;
    List<StractHospital> myList;
    HospitalAdapter hospitalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospital_list);

        Toolbar toolbar = findViewById(R.id.search_toolbarAH);
        setSupportActionBar(toolbar);


        //ListView listHospital = findViewById(R.id.listHospital);
//       RecyclerView listHospital = findViewById(R.id.listHospital);
//       setAdapter(listHospital);
//        DatabaseHelper mDBHELPER = new DatabaseHelper(MainActivity.this, "Hospital");
//        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
//        if (database.exists() == false) {
//            mDBHELPER.getReadableDatabase();
////            if (!copydatabase(MainActivity.this)) {
////                return;
////            }
//        }
//        arrayList = mDBHELPER.GetHospitals();
//        adapter = new HospitalAdapter(arrayList);
//        adapter.notifyDataSetChanged();
//        listHospital.setAdapter(adapter,this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        displayItem();

    }

    private void displayItem() {
        recyclerView = (RecyclerView) findViewById(R.id.listHospital);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHelper mDBHELPER = new DatabaseHelper(All_Hospital_list.this, "Hospital");
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (!database.exists()) {
            mDBHELPER.getReadableDatabase();
            if (!database.exists()) {
            mDBHELPER.getReadableDatabase();
            if (!copydatabase(All_Hospital_list.this)) {
                return;
            }
                 }
        }
        myList = mDBHELPER.GetHospitals();
        hospitalAdapter=new HospitalAdapter(myList,this);
        hospitalAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(hospitalAdapter);

    }

//    private void setAdapter(RecyclerView listHospital) {
//
//    }

    public Boolean copydatabase(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String OutFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
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
                hospitalAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
}
