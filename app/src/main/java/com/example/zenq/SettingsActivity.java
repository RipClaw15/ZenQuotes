package com.example.zenq;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox checkboxPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkboxPreference = findViewById(R.id.checkboxPreference);

        // Load saved preferences and update UI
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFeatureEnabled = sharedPreferences.getBoolean("feature_enabled", false);
        checkboxPreference.setChecked(isFeatureEnabled);

        // Handle changes to preferences
        checkboxPreference.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the new preference value
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("feature_enabled", isChecked);
            editor.apply();
        });
    }
}