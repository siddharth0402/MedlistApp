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

public class ComplainAcitivty extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ComplainAdapter complainAdapter;
    ArrayList<UserComplain> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_acitivty);
        recyclerView = findViewById(R.id.complain_list);
        recyclerView.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference("Complain");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<>();
        complainAdapter = new ComplainAdapter(this,list);
        recyclerView.setAdapter(complainAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserComplain user = dataSnapshot.getValue(UserComplain.class);
                    list.add(user);
                }
                complainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}