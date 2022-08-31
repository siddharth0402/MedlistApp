package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {
    ImageView splashbg;
    TextView appname;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appname = findViewById(R.id.appname);
        splashbg = findViewById(R.id.imageView2);
        lottieAnimationView = findViewById(R.id.lotiiee);
        splashbg.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        appname.animate().translationY(-2000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(-1500).setDuration(1000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen.this,MainActivity.class));
            }
        },5000);

    }
}