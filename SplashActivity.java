// File: app/src/main/java/com/fwfamilywear/activities/SplashActivity.java
package com.fwfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.fwfamilywear.session.SessionManager;

public class SplashActivity extends AppCompatActivity {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new SessionManager(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.isLoggedIn()) {
                    String role = session.getUserDetails().get(SessionManager.KEY_ROLE);
                    if ("Admin".equals(role)) {
                        startActivity(new Intent(SplashActivity.this, AdminDashboardActivity.class));
                    } else if ("Moderator".equals(role)) {
                        startActivity(new Intent(SplashActivity.this, ModeratorDashboardActivity.class));
                    } else if ("Vendor".equals(role)) {
                        startActivity(new Intent(SplashActivity.this, VendorDashboardActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 2000); // 2-second splash screen
    }
}
