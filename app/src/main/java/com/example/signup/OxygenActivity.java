package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OxygenActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    OxygenAdapter oxygenadapter;
    ArrayList<OXYUSER> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);
        recyclerView = findViewById(R.id.oxygenList);
        recyclerView.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference("OXY");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<>();
        oxygenadapter = new OxygenAdapter(this,list);
        recyclerView.setAdapter(oxygenadapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    OXYUSER user = dataSnapshot.getValue(OXYUSER.class);
                    list.add(user);
                }
                oxygenadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}