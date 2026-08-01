package com.example.azkar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FAVORITES = "favorite_quotes";

    private LinearLayout favoritesContainer;
    private TextView tvEmptyFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        View root = findViewById(R.id.rootFavorites);
        ThemeHelper.applyBackground(this, root);

        favoritesContainer = findViewById(R.id.favoritesContainer);
        tvEmptyFavorites = findViewById(R.id.tvEmptyFavorites);
        TextView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        loadFavorites();
    }

    private void loadFavorites() {
        favoritesContainer.removeAllViews();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> favorites = prefs.getStringSet(KEY_FAVORITES, new HashSet<>());

        if (favorites.isEmpty()) {
            tvEmptyFavorites.setVisibility(View.VISIBLE);
            return;
        }

        tvEmptyFavorites.setVisibility(View.GONE);

        for (String quote : favorites) {
            favoritesContainer.addView(buildFavoriteRow(quote));
        }
    }

    private LinearLayout buildFavoriteRow(String quote) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(20, 20, 20, 20);
        row.setBackgroundResource(R.drawable.ic_card);

        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 0, 0, 12);
        row.setLayoutParams(rowParams);

        TextView tvQuote = new TextView(this);
        tvQuote.setText(quote);
        tvQuote.setTextColor(getColor(R.color.brown_dark));
        tvQuote.setTextSize(15);
        LinearLayout.LayoutParams quoteParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        tvQuote.setLayoutParams(quoteParams);

        TextView btnRemove = new TextView(this);
        btnRemove.setText("✕");
        btnRemove.setTextColor(getColor(R.color.brown_medium));
        btnRemove.setTextSize(18);
        btnRemove.setPadding(24, 0, 0, 0);
        btnRemove.setOnClickListener(v -> removeFavorite(quote));

        row.addView(tvQuote);
        row.addView(btnRemove);

        return row;
    }

    private void removeFavorite(String quote) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> favorites = new HashSet<>(prefs.getStringSet(KEY_FAVORITES, new HashSet<>()));
        favorites.remove(quote);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();

        loadFavorites();
    }
}