package com.example.azkar;

public class Category {

    private String name;
    private String count;
    private boolean isFavorites;

    public Category(String name, String count, boolean isFavorites) {
        this.name = name;
        this.count = count;
        this.isFavorites = isFavorites;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public boolean isFavorites() {
        return isFavorites;
    }
}