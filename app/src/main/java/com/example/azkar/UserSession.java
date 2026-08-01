package com.example.azkar;

import android.content.Context;

public class UserSession {

     static final String PREFS_NAME = "azkar_prefs";
     static final String KEY_USER_NAME = "user_name";
     static final String KEY_USER_PHOTO = "user_photo_uri";

    public static String getUserName(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_USER_NAME, "");
    }

    public static void saveUserName(Context context, String name) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putString(KEY_USER_NAME, name).apply();
    }

    public static String getUserPhotoUri(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_USER_PHOTO, null);
    }

    public static void saveUserPhotoUri(Context context, String uri) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putString(KEY_USER_PHOTO, uri).apply();
    }
}