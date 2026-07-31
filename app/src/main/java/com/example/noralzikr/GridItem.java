package com.example.noralzikr;

public class GridItem {
    private String title;
    private int iconResId;

    public GridItem(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public String getTitle() { return title; }
    public int getIconResId() { return iconResId; }
}
