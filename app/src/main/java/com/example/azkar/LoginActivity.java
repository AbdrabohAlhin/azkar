package com.example.azkar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "azkar_prefs";
    public static final String KEY_USER_NAME = "user_name";

     EditText etUserName;
     ImageView ivAvatar;
     Uri selectedPhotoUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.etUserName);
        ivAvatar = findViewById(R.id.ivAvatar);
        Button btnLogin = findViewById(R.id.btnLogin);
        FrameLayout avatarWrap = findViewById(R.id.avatarWrap);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = prefs.getString(KEY_USER_NAME, null);
        if (savedName != null) {
            goToMain();
            return;
        }

        avatarWrap.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        btnLogin.setOnClickListener(v -> attemptLogin());
    }
    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    try {
                        getContentResolver().takePersistableUriPermission(
                                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } catch (Exception e) {
                    }
                    selectedPhotoUri = uri;
                    ivAvatar.setImageURI(uri);
                    AvatarHelper.makeCircular(ivAvatar);
                }
            });

    private void attemptLogin() {
        String name = etUserName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, R.string.please_enter_name, Toast.LENGTH_SHORT).show();
            return;
        }

        UserSession.saveUserName(this, name);

        if (selectedPhotoUri != null) {
            UserSession.saveUserPhotoUri(this, selectedPhotoUri.toString());
        }

        goToMain();
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}