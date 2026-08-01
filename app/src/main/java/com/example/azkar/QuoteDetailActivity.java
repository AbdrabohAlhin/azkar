package com.example.azkar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuoteDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "extra_category";

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FAVORITES = "favorite_quotes";
    private static final String KEY_FONT_SIZE = "font_size";
    private static final String KEY_QUOTES_READ = "quotes_read_count";

    private TextView tvQuote;
    private TextView btnFavorite;
    private String currentQuote;
    private boolean isFavorite = false;

    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);

        View root = findViewById(R.id.rootQuoteDetail);
        ThemeHelper.applyBackground(this, root);

        tvQuote = findViewById(R.id.tvQuote);
        TextView tvCategoryPill = findViewById(R.id.tvCategoryPill);
        btnFavorite = findViewById(R.id.btnFavorite);
        TextView btnCopy = findViewById(R.id.btnCopy);
        TextView btnShare = findViewById(R.id.btnShare);
        TextView btnBack = findViewById(R.id.btnBack);

        String currentCategory = getIntent().getStringExtra(EXTRA_CATEGORY);
        if (currentCategory == null) currentCategory = "تحفيزي";

        String[] categoryQuotes = QuoteBank.getQuotesForCategory(currentCategory);
        currentQuote = categoryQuotes[random.nextInt(categoryQuotes.length)];

        tvCategoryPill.setText(currentCategory);
        tvQuote.setText(currentQuote);
        applyFontSize();

        increaseQuotesReadCount();

        isFavorite = isQuoteFavorite(currentQuote);
        updateFavoriteIcon();

        btnBack.setOnClickListener(v -> finish());
        btnFavorite.setOnClickListener(v -> toggleFavorite());
        btnCopy.setOnClickListener(v -> copyQuote());
        btnShare.setOnClickListener(v -> shareQuote());
    }

    private void applyFontSize() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int fontIndex = prefs.getInt(KEY_FONT_SIZE, 1);

        if (fontIndex == 0) {
            tvQuote.setTextSize(20);
        } else if (fontIndex == 1) {
            tvQuote.setTextSize(28);
        } else {
            tvQuote.setTextSize(34);
        }
    }

    private void increaseQuotesReadCount() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int count = prefs.getInt(KEY_QUOTES_READ, 0);
        prefs.edit().putInt(KEY_QUOTES_READ, count + 1).apply();
    }

    private void toggleFavorite() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> favorites = new HashSet<>(prefs.getStringSet(KEY_FAVORITES, new HashSet<>()));

        if (isFavorite) {
            favorites.remove(currentQuote);
            Toast.makeText(this, R.string.removed_from_favorites, Toast.LENGTH_SHORT).show();
        } else {
            favorites.add(currentQuote);
            Toast.makeText(this, R.string.added_to_favorites, Toast.LENGTH_SHORT).show();
        }

        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
        isFavorite = !isFavorite;
        updateFavoriteIcon();
    }

    private boolean isQuoteFavorite(String quote) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>()).contains(quote);
    }

    private void updateFavoriteIcon() {
        btnFavorite.setText(isFavorite ? "♥" : "♡");
    }

    private void copyQuote() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("quote", currentQuote);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show();
    }

    private void shareQuote() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }
}