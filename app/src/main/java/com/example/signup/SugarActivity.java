package com.example.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SugarActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    SUGARADAPTER sugaradapter;
    ArrayList<SUGARUSER> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar2);
        recyclerView = findViewById(R.id.sugar_list);
        recyclerView.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference("USER_S");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<>();
        sugaradapter = new SUGARADAPTER(this,list);
        recyclerView.setAdapter(sugaradapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    SUGARUSER user = dataSnapshot.getValue(SUGARUSER.class);
                    list.add(user);
                }
                sugaradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}