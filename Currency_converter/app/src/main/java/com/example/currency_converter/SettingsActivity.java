package com.example.currency_converter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch switchTheme = findViewById(R.id.switchTheme);
        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean isDark = pref.getBoolean("darkTheme", false);
        switchTheme.setChecked(isDark);

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("darkTheme", isChecked);
            editor.apply();

            AppCompatDelegate.setDefaultNightMode(isChecked ?
                    AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });
    }
}
