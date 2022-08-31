package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Below API 29 (While Pasting the code delete this comment)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        mFirebaseAuth = FirebaseAuth.getInstance();
        myRef.setValue("Hello, World!");
        Button button = findViewById(R.id.loginn);
        button.setOnClickListener(view -> openlogin_page());
        Button button2 = findViewById(R.id.signupbtn);
        button2.setOnClickListener(view -> opensignin_page());

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            startActivity(new Intent(this,home_screen.class));
        }
        else {

        }
    }
    public void openlogin_page(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
    public void opensignin_page(){
        Intent intent = new Intent(this, Signuppage.class);
        startActivity(intent);

    }
}
