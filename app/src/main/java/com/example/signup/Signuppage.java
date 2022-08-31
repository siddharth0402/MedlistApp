package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signuppage extends AppCompatActivity {
    public static final  String TAG ="TAG";
    EditText mUsername, mAadhar, mEmail, mMobile, mPassword, mRepassword;
    Button mRegisterButton;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userId;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        mUsername = findViewById(R.id.username);
        mAadhar = findViewById(R.id.aadhar);
        mEmail = findViewById(R.id.email);
        mMobile = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);
        mRepassword = findViewById(R.id.confirmPassword);
        mRegisterButton = findViewById(R.id.signBtn);
        progressBar = findViewById(R.id.progressBar);
        //Below API 29 (While Pasting the code delete this comment)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mUsername.getText().toString().trim();
                final String aadhar = mAadhar.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String mobile = mMobile.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String repassword = mRepassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    mUsername.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(aadhar)) {
                    mAadhar.setError("Aadhar is required");
                    return;
                }
                if(aadhar.length()<12) {
                    mAadhar.setError("Aadhar No must be greater than 12 digit");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    mMobile.setError("Phone No is required");
                    return;
                }
                if(mobile.length() != 10) {
                    mMobile.setError("Mobile No must be of 10 digit");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()<8){
                    mPassword.setError("Password must be greater than 8 digit");
                }
                if (!repassword.equals(password)) {
                    mRepassword.setError("Password not match");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email Not Send");
                                }
                            });
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            userId =fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("user").document(userId);
                            Map<String,Object> user =new HashMap<>();
                            user.put("Username",username);
                            user.put("Aadhar",aadhar);
                            user.put("Email",email);
                            user.put("Mobile No",mobile);
                            user.put("Password",password);
                            user.put("RePassword",repassword);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: User profile is created for " + userId);
                                 }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),home_screen.class));


                        }
                        else {
                            Toast.makeText(Signuppage.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}