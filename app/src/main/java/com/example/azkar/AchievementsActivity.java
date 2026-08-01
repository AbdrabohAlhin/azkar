package com.example.azkar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AchievementsActivity extends AppCompatActivity {

    private TextView txtLoginStatus, txtAzkarStatus, txtFavoriteStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        txtLoginStatus = findViewById(R.id.txtLoginStatus);
        txtAzkarStatus = findViewById(R.id.txtAzkarStatus);
        txtFavoriteStatus = findViewById(R.id.txtFavoriteStatus);

        SharedPreferences prefs = getSharedPreferences("achievements", MODE_PRIVATE);

        boolean loginDone = prefs.getBoolean("login", false);
        boolean azkarDone = prefs.getBoolean("first_dhikr", false);
        boolean favoriteDone = prefs.getBoolean("first_favorite", false);

        txtLoginStatus.setText(loginDone ?
                "✅ تم الإنجاز" :
                "🔒 لم يتم الإنجاز بعد");

        txtAzkarStatus.setText(azkarDone ?
                "✅ تم الإنجاز" :
                "🔒 لم يتم الإنجاز بعد");

        txtFavoriteStatus.setText(favoriteDone ?
                "✅ تم الإنجاز" :
                "🔒 لم يتم الإنجاز بعد");
    }
}