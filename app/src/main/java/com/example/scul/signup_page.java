package com.example.scul;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_page extends AppCompatActivity {
    EditText firstName, lastName, email, password;
    Button registerButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email= findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), login_page.class));
            finish();
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mpassword = password.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(mpassword)){
                    password.setError("Password is required");
                    return;
                }
                if (mpassword.length() < 6){
                    password.setError("Password must be atleast 6 characters.");
                }


                fAuth.createUserWithEmailAndPassword(mEmail, mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup_page.this, "User is created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), login_page.class));
                        }else{
                            Toast.makeText(signup_page.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

    }
}
