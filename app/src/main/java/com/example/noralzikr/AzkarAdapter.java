package com.example.noralzikr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class AzkarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Dhikr> dhikrList;

    public AzkarAdapter(Context context, ArrayList<Dhikr> dhikrList) {
        this.context = context;
        this.dhikrList = dhikrList;
    }

    @Override
    public int getCount() { return dhikrList.size(); }

    @Override
    public Object getItem(int position) { return dhikrList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.azkar_item, parent, false);
        }

        Dhikr currentDhikr = dhikrList.get(position);

        TextView tvCounter = convertView.findViewById(R.id.tvCounter);
        TextView tvText = convertView.findViewById(R.id.tvDhikrText);
        ImageView btnFav = convertView.findViewById(R.id.btnFavorite);


        tvText.setText(currentDhikr.getText());
        tvCounter.setText(String.valueOf(currentDhikr.getTargetCount() - currentDhikr.getCurrentCount()));


        tvCounter.setOnClickListener(v -> {
            int current = currentDhikr.getCurrentCount();
            if (current < currentDhikr.getTargetCount()) {
                currentDhikr.setCurrentCount(current + 1);
                notifyDataSetChanged();
            }
        });


        btnFav.setOnClickListener(v -> {
            currentDhikr.setFavorite(!currentDhikr.isFavorite());

            notifyDataSetChanged();
        });

        return convertView;
    }
}
