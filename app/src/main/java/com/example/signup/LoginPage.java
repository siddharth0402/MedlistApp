package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    EditText  mEmail, mPassword;
    Button mloginButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
//Below API 29 (While Pasting the code delete this comment)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mEmail = findViewById(R.id.login_id);
        mPassword = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progressBar2);
        mloginButton =findViewById(R.id.login_button);
        fAuth= FirebaseAuth.getInstance();


        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_id= mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();

                if (TextUtils.isEmpty((login_id))){
                    mEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Enter the password");
                    return;
                }

                if ( password.length()<8)
                {
                    mPassword.setError("Password must be >= 8 character");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //Sid yaha pe change kara kyuki wo method galat tha ye wallah sahi he
                fAuth.signInWithEmailAndPassword(login_id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"log in successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),home_screen.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "error"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });









    }
}