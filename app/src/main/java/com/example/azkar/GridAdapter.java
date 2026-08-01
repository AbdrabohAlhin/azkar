package com.example.azkar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GridItem> list;

    public GridAdapter(Context context, ArrayList<GridItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        GridItem currentItem = list.get(position);

        ImageView icon = convertView.findViewById(R.id.itemIcon);
        TextView title = convertView.findViewById(R.id.itemTitle);

        icon.setImageResource(currentItem.getIconResId());
        title.setText(currentItem.getTitle());

        return convertView;
    }
}
