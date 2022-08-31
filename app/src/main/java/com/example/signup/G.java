package com.example.signup;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatDelegate;

public class G extends Application {
    public static LayoutInflater inflater;
    public static Context context;
    @Override
    public void onCreate() {
        //Below API 29 (While Pasting the code delete this comment)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
}
