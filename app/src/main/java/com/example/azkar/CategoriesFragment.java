package com.example.azkar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;

public class CategoriesFragment extends Fragment {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FAVORITES = "favorite_quotes";

    private TextView tvFavoritesCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThemeHelper.applyBackground(requireContext(), view);
        setupHeader(view);

        tvFavoritesCount = view.findViewById(R.id.tvFavoritesCount);
        updateFavoritesCount();

        view.findViewById(R.id.layoutMotivation).setOnClickListener(v -> openCategory("تحفيزي"));
        view.findViewById(R.id.layoutSuccess).setOnClickListener(v -> openCategory("نجاح"));
        view.findViewById(R.id.layoutLiterature).setOnClickListener(v -> openCategory("أدب"));
        view.findViewById(R.id.layoutWisdom).setOnClickListener(v -> openCategory("حكم"));
        view.findViewById(R.id.layoutFavorites).setOnClickListener(v ->
                startActivity(new Intent(requireContext(), FavoritesActivity.class))
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavoritesCount();
    }

    private void setupHeader(View view) {
        ImageView ivHeaderAvatar = view.findViewById(R.id.ivHeaderAvatar);
        TextView tvHeaderGreeting = view.findViewById(R.id.tvHeaderGreeting);

        String name = UserSession.getUserName(requireContext());
        tvHeaderGreeting.setText("السلام عليكم يا " + name);
        AvatarHelper.loadUserAvatar(requireContext(), ivHeaderAvatar);
    }

    private void updateFavoritesCount() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int count = prefs.getStringSet(KEY_FAVORITES, new HashSet<>()).size();
        tvFavoritesCount.setText(count + " اقتباس محفوظ");
    }

    private void openCategory(String categoryName) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).openQuoteDetail(categoryName);
        }
    }
}