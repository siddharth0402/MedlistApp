package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class qr_generator extends AppCompatActivity {

    EditText qrvalue;
    Button generateBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        qrvalue = findViewById(R.id.editText);
        generateBtn = findViewById(R.id.generateButton);
        qrImage = findViewById(R.id.qrImage);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = qrvalue.getText().toString();

            }
        });
    }
}