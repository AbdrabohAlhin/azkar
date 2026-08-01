package com.example.azkar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FONT_SIZE = "font_size";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_NOTIFICATIONS = "notifications";

    private final String[] fontLabels = {"صغير", "متوسط", "كبير"};

    private TextView tvFontSize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThemeHelper.applyBackground(requireContext(), view);

        LinearLayout rowFontSize = view.findViewById(R.id.rowFontSize);
        LinearLayout rowHelp = view.findViewById(R.id.rowHelp);
        tvFontSize = view.findViewById(R.id.tvFontSize);
        Switch switchDarkMode = view.findViewById(R.id.switchDarkMode);
        Switch switchNotifications = view.findViewById(R.id.switchNotifications);

        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        int fontIndex = prefs.getInt(KEY_FONT_SIZE, 1);
        tvFontSize.setText(fontLabels[fontIndex]);

        switchDarkMode.setChecked(prefs.getBoolean(KEY_DARK_MODE, false));
        switchNotifications.setChecked(prefs.getBoolean(KEY_NOTIFICATIONS, true));

        rowFontSize.setOnClickListener(v -> {
            int current = prefs.getInt(KEY_FONT_SIZE, 1);
            int next = (current + 1) % fontLabels.length;
            prefs.edit().putInt(KEY_FONT_SIZE, next).apply();
            tvFontSize.setText(fontLabels[next]);
            Toast.makeText(requireContext(), "تم تغيير حجم الخط إلى " + fontLabels[next], Toast.LENGTH_SHORT).show();
        });

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply();
            ThemeHelper.applyBackground(requireContext(), view);
        });

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply();

            if (isChecked) {
                Toast.makeText(requireContext(), "تم تفعيل التنبيهات", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "تم إيقاف التنبيهات", Toast.LENGTH_SHORT).show();
            }
        });

        rowHelp.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), HelpActivity.class))
        );
        LinearLayout rowAbout = view.findViewById(R.id.rowAbout);

        rowAbout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutActivity.class);
            startActivity(intent);
        });
        LinearLayout rowAccount = view.findViewById(R.id.rowAccount);

        rowAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            startActivity(intent);
        });
        rowAccount = view.findViewById(R.id.rowAccount);
        LinearLayout rowAchievements = view.findViewById(R.id.rowAchievements);

        rowAchievements.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AchievementsActivity.class);
            startActivity(intent);
        });

        LinearLayout rowContact = view.findViewById(R.id.rowContact);
        rowContact.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContactUsActivity.class);
            startActivity(intent);
        });
    }


}