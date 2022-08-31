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

public class KHOON extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference database;
KHOONADAPTER khoonadapter;
ArrayList<KHOONUSER> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoon);
        recyclerView = findViewById(R.id.khoonList);
        recyclerView.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference("user");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<>();
        khoonadapter = new KHOONADAPTER(this,list);
        recyclerView.setAdapter(khoonadapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    KHOONUSER user = dataSnapshot.getValue(KHOONUSER.class);
                    list.add(user);
                }
                khoonadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}