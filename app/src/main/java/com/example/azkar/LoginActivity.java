package com.example.azkar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "azkar_prefs";
    public static final String KEY_USER_NAME = "user_name";

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.etUserName);
        Button btnLogin = findViewById(R.id.btnLogin);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = prefs.getString(KEY_USER_NAME, null);
        if (savedName != null) {
            goToMain();
            return;
        }

        btnLogin.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String name = etUserName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, R.string.please_enter_name, Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putString(KEY_USER_NAME, name).apply();

        SharedPreferences achievementPrefs = getSharedPreferences("achievements", MODE_PRIVATE);
        achievementPrefs.edit().putBoolean("login", true).apply();

        goToMain();
    }


    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}