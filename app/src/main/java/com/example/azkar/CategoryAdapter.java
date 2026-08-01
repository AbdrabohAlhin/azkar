package com.example.azkar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_category, parent, false);
        }

        Category category = getItem(position);

        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvCount = convertView.findViewById(R.id.tvItemCount);

        if (category != null) {
            tvName.setText(category.getName());
            tvCount.setText(category.getCount());
        }

        return convertView;
    }
}