package com.example.scul;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class login_page extends AppCompatActivity {
    EditText EmailAddress, Password;
    Button loginButton;
    FirebaseAuth fAuth;
    TextView signUpButton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);



        EmailAddress = findViewById(R.id.loginEmailId);
        Password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        fAuth = FirebaseAuth.getInstance();
        signUpButton = findViewById(R.id.signUp_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = EmailAddress.getText().toString().trim();
                String LoginPassword = Password.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    EmailAddress.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(LoginPassword)){
                    Password.setError("Password is required");
                    return;
                }
                if (Password.length() < 6){
                    Password.setError("Password must be atleast 6 characters.");
                }

                fAuth.signInWithEmailAndPassword(Email, LoginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), mainpage.class));


                        }else {
                            Toast.makeText(login_page.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), signup_page.class);
                startActivity(intent1);





            }
        });
    }
}
