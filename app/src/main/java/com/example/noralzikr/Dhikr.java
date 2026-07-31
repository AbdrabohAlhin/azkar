package com.example.noralzikr;

public class Dhikr {
    private int id;
    private String text;
    private int targetCount;
    private int currentCount;
    private boolean isFavorite;


    public Dhikr(int id, String text, int targetCount) {
        this.id = id;
        this.text = text;
        this.targetCount = targetCount;
        this.currentCount = 0;
        this.isFavorite = false;
    }


    public int getId() { return id; }
    public String getText() { return text; }
    public int getTargetCount() { return targetCount; }

    public int getCurrentCount() { return currentCount; }
    public void setCurrentCount(int currentCount) { this.currentCount = currentCount; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
