package com.example.azkar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;

public class ThemeHelper {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";

    public static boolean isDarkMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_DARK_MODE, false);
    }

    public static void applyBackground(Context context, View rootView) {
        if (rootView == null) return;

        if (isDarkMode(context)) {
            rootView.setBackgroundColor(Color.BLACK);
        } else {
            rootView.setBackgroundColor(context.getResources().getColor(R.color.bg_beige));
        }
    }
}