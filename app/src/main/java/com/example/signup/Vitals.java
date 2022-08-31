package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Vitals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
        ImageButton buttonB = findViewById(R.id.BP);
        buttonB.setOnClickListener(view -> openBP());
        ImageButton buttonS = findViewById(R.id.SUGAR);
        buttonS.setOnClickListener(view -> openSugar());
        ImageButton buttonOxy = findViewById(R.id.O2);
        buttonOxy.setOnClickListener(view -> openOxy());
        ImageButton buttonO = findViewById(R.id.OTHER);
        buttonO.setOnClickListener(view ->openOther());
    }

    private void openOther() {
        Intent intent = new Intent(this, upd.class);
        startActivity(intent);
    }

    private void openOxy() {
        Intent intent = new Intent(this, Oxy.class);
        startActivity(intent);
    }

    private void openSugar() {
        Intent intent = new Intent(this, Sugar.class);
        startActivity(intent);
    }

    private void openBP() {
        Intent intent = new Intent(this, BP.class);
        startActivity(intent);
    }
}