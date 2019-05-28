package com.example.nfccardemulation;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_scan_card:
                    Intent a = new Intent(NotificationActivity.this, GeneralActivity.class);
                    startActivity(a);
                    break;
                case R.id.navigation_reports:
                    Intent b = new Intent(NotificationActivity.this, ReportsActivity.class);
                    startActivity(b);
                    break;
                case R.id.navigation_notifications:
                    navigation.setSelectedItemId(R.id.navigation_notifications);
                    break;
            }
            return false;
        });
    }
}
