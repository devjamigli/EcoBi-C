package com.venusdev.ecobi_c.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.venusdev.ecobi_c.ui.main.MapsActivity;
import com.venusdev.ecobi_c.ui.onboarding.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean("firstTime",false)){
            startActivity(new Intent(this, OnBoardingActivity.class));
        } else {
            startActivity(new Intent(this, MapsActivity.class));
        }
        finish();
    }
}
