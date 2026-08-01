package com.example.azkar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private TextView navToday, navCategories, navStats, navSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navToday = findViewById(R.id.navToday);
        navCategories = findViewById(R.id.navCategories);
        navStats = findViewById(R.id.navStats);
        navSettings = findViewById(R.id.navSettings);

        navToday.setOnClickListener(v -> selectTab(navToday, new TodayFragment()));
        navCategories.setOnClickListener(v -> selectTab(navCategories, new CategoriesFragment()));
        navStats.setOnClickListener(v -> selectTab(navStats, new StatsFragment()));
        navSettings.setOnClickListener(v -> selectTab(navSettings, new SettingsFragment()));

        if (savedInstanceState == null) {
            selectTab(navToday, new TodayFragment());
        }
    }

    private void selectTab(TextView selected, Fragment fragment) {
        navToday.setTextColor(getColor(R.color.brown_medium));
        navCategories.setTextColor(getColor(R.color.brown_medium));
        navStats.setTextColor(getColor(R.color.brown_medium));
        navSettings.setTextColor(getColor(R.color.brown_medium));
        navToday.setTypeface(null, Typeface.NORMAL);
        navCategories.setTypeface(null, Typeface.NORMAL);
        navStats.setTypeface(null, Typeface.NORMAL);
        navSettings.setTypeface(null, Typeface.NORMAL);

        selected.setTextColor(getColor(R.color.brown_dark));
        selected.setTypeface(null, Typeface.BOLD);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void openQuoteDetail(String categoryName) {
        Intent intent = new Intent(this, QuoteDetailActivity.class);
        intent.putExtra(QuoteDetailActivity.EXTRA_CATEGORY, categoryName);
        startActivity(intent);
    }
}