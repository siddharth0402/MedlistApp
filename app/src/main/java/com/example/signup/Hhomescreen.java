package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Hhomescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Below API 29 (While Pasting the code delete this comment)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhomescreen);
        ImageButton buttonA = findViewById(R.id.all);
        buttonA.setOnClickListener(view -> openAll());
        ImageButton buttonS = findViewById(R.id.state);
        buttonS.setOnClickListener(view -> openState());
        ImageButton buttonC = findViewById(R.id.city);
        buttonC.setOnClickListener(view -> openCity());
        ImageButton buttonB = findViewById(R.id.beds_avaliable);
        buttonB.setOnClickListener(view-> openBeds());
    }

    private void openBeds() {
        Intent intent = new Intent( this, beds_home.class);  //beds_home
        startActivity( intent);
    }

    private void openCity() {
        Intent intent = new Intent(this, city.class);
        startActivity(intent);
    }

    private void openState() {
        Intent intent = new Intent(this, States.class);
        startActivity(intent);
    }

    private void openAll() {
        Intent intent = new Intent(this, All_Hospital_list.class);
        startActivity(intent);
    }
}