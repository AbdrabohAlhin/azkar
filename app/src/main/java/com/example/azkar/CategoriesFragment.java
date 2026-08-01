package com.example.azkar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private static final String PREFS_NAME = "azkar_prefs";
    private static final String KEY_FAVORITES = "favorite_quotes";

    private ListView lvCategories;
     CategoryAdapter adapter;
    private List<Category> categoryList;

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

        lvCategories = view.findViewById(R.id.lvCategories);

        buildCategoryList();
        adapter = new CategoryAdapter(requireContext(), categoryList);
        lvCategories.setAdapter(adapter);

        lvCategories.setOnItemClickListener((parent, itemView, position, id) -> {
            Category selected = categoryList.get(position);

            if (selected.isFavorites()) {
                startActivity(new Intent(requireContext(), FavoritesActivity.class));
            } else if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openQuoteDetail(selected.getName());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        buildCategoryList();
        adapter.notifyDataSetChanged();
    }

    private void buildCategoryList() {
        int favoritesCount = getFavoritesCount();

        if (categoryList == null) {
            categoryList = new ArrayList<>();
        } else {
            categoryList.clear();
        }

        categoryList.add(new Category("تحفيزي", "٤٢ اقتباس", false));
        categoryList.add(new Category("نجاح", "٣٠ اقتباس", false));
        categoryList.add(new Category("أدب", "٥٥ اقتباس", false));
        categoryList.add(new Category("حكم", "٣٨ اقتباس", false));
        categoryList.add(new Category("المفضلة", favoritesCount + " اقتباس محفوظ", true));
    }

    private int getFavoritesCount() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>()).size();
    }
}