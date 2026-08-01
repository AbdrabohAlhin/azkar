package com.example.azkar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        // الصفحة الافتراضية
        loadFragment(new TodayFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {

            Fragment fragment = null;

            int id = item.getItemId();

            if (id == R.id.nav_today) {
                fragment = new TodayFragment();

            } else if (id == R.id.nav_azkar) {
                fragment = new AzkarFragment();

            } else if (id == R.id.nav_categories) {
                fragment = new CategoriesFragment();

            } else if (id == R.id.nav_stats) {
                fragment = new StatsFragment();

            } else if (id == R.id.nav_settings) {
                fragment = new SettingsFragment();
            }
            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }

            return false;
        });

    }

    private void loadFragment(Fragment fragment) {

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