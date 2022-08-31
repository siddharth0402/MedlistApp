package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Oxy extends AppCompatActivity {

    Button btnInsert , btnView;
    EditText date, oxygen, notes;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxy);

        btnInsert = findViewById( R.id.btn_save);
        btnView = findViewById( R.id.btn_view);
        date = findViewById(R.id.date);
        oxygen = findViewById( R.id.oxygen_level);
        notes = findViewById( R.id.notes);

        databaseUsers = FirebaseDatabase.getInstance().getReference();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Oxy.this,OxygenActivity.class));
                finish();
            }
        });
    }

    private void InsertData() {

        String userdate = date.getText().toString();
        String userOxy = oxygen.getText().toString();
        String usernote = notes.getText().toString();
        String id = databaseUsers.push().getKey();

        USEROXY user = new USEROXY( userdate, userOxy, usernote);
        databaseUsers.child("OXY").child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if ( task.isSuccessful()){
                            Toast.makeText(Oxy.this, "user details inserted ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}