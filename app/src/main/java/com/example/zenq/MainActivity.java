package com.example.zenq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDailyQuote = findViewById(R.id.buttonDailyQuote);
        Button buttonRandomQuotes = findViewById(R.id.buttonRandomQuotes);
        Button btnSettings = findViewById(R.id.btnSettings);
        Button btnOpenZenQuotes = findViewById(R.id.btnOpenZenQuotes);

        btnOpenZenQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ZenQuotes.io website in a web browser
                String url = "https://zenquotes.io/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the settings activity when the button is clicked
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonDailyQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens page "Quote of the day"
                Intent intent = new Intent(MainActivity.this, DailyQuoteActivity.class);
                startActivity(intent);
            }
        });

        buttonRandomQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens page "Random Quotes"
                Intent intent = new Intent(MainActivity.this, RandomQuotesActivity.class);
                startActivity(intent);
            }
        });
    }
}
