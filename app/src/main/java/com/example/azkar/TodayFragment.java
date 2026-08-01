package com.example.azkar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class TodayFragment extends Fragment {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FONT_SIZE = "font_size";

    private static final String[] QUOTES = {
            "لا تحزن إن الله معنا",
            "من جدّ وجد، ومن زرع حصد",
            "الصبر مفتاح الفرج",
            "ثق بالله وامضِ قدمًا",
            "كل يوم جديد فرصة جديدة"
    };

    private TextView tvQuote;
    private final Random random = new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThemeHelper.applyBackground(requireContext(), view);
        setupHeader(view);

        tvQuote = view.findViewById(R.id.tvQuote);
        Button btnNewQuote = view.findViewById(R.id.btnNewQuote);

        applyFontSize();

        btnNewQuote.setOnClickListener(v -> showRandomQuote());
    }

    @Override
    public void onResume() {
        super.onResume();
        applyFontSize();
    }

    private void setupHeader(View view) {
        ImageView ivHeaderAvatar = view.findViewById(R.id.ivHeaderAvatar);
        TextView tvHeaderGreeting = view.findViewById(R.id.tvHeaderGreeting);

        String name = UserSession.getUserName(requireContext());
        tvHeaderGreeting.setText("السلام عليكم  " + name);
        AvatarHelper.loadUserAvatar(requireContext(), ivHeaderAvatar);
    }

    private void showRandomQuote() {
        String quote = QUOTES[random.nextInt(QUOTES.length)];
        tvQuote.setText(quote);
    }

    private void applyFontSize() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int fontIndex = prefs.getInt(KEY_FONT_SIZE, 1);

        if (fontIndex == 0) {
            tvQuote.setTextSize(18);
        } else if (fontIndex == 1) {
            tvQuote.setTextSize(24);
        } else {
            tvQuote.setTextSize(30);
        }
    }
}