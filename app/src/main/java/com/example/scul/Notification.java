package com.example.scul;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.notification);

       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.notification:
                       return true;


                   case R.id.home:
                       startActivity(new Intent(getApplicationContext(), mainpage.class));
                       overridePendingTransition(0, 0);
                       return true;
                   case R.id.classes:
                       startActivity(new Intent(getApplicationContext(), Classes.class));
                       overridePendingTransition(0, 0);
                       return true;
                   case R.id.account:
                       startActivity(new Intent(getApplicationContext(), Account.class));
                       overridePendingTransition(0, 0);
                       return true;
               }
               return false;
           }
       });
    }
}
