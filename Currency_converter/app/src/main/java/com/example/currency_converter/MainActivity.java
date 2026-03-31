package com.example.currencyconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    EditText etAmount;
    Spinner spinnerFrom, spinnerTo;
    Button btnConvert, btnSettings;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load theme before super.onCreate
        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean isDark = pref.getBoolean("darkTheme", false);
        AppCompatDelegate.setDefaultNightMode(isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        btnSettings = findViewById(R.id.btnSettings);
        tvResult = findViewById(R.id.tvResult);

        btnConvert.setOnClickListener(v -> convertCurrency());

        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void convertCurrency() {
        String input = etAmount.getText().toString();
        if (input.isEmpty()) return;

        double amount = Double.parseDouble(input);
        String from = spinnerFrom.getSelectedItem().toString();
        String to = spinnerTo.getSelectedItem().toString();
        double[] rates = {83.0, 1.0, 150.0, 0.92};
        String[] codes = {"INR", "USD", "JPY", "EUR"};
        int fromIdx = 0, toIdx = 0;
        for (int i = 0; i < 4; i++) {
            if (codes[i].equals(from)) fromIdx = i;
            if (codes[i].equals(to)) toIdx = i;
        }

        double result = (amount / rates[fromIdx]) * rates[toIdx];
        tvResult.setText(String.format("Result: %.2f %s", result, to));
    }
}