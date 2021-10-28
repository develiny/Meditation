package com.develiny.meditation.page.item;

public class FavTitleItem {
    String title;
    int isplay;

    public FavTitleItem() {}

    public FavTitleItem(String title, int isplay) {
        this.title = title;
        this.isplay = isplay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsplay() {
        return isplay;
    }

    public void setIsplay(int isplay) {
        this.isplay = isplay;
    }
}
