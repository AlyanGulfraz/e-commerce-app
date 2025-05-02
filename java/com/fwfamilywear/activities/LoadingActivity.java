package com.fmfamilywear.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.fmfamilywear.R;
import com.fwfamilywear.activities.SplashActivity;

public class LoadingActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_loading);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }, 800);
    }
}
