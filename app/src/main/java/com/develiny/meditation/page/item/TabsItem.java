package com.develiny.meditation.page.item;

public class TabsItem {

    int img;
    String title;
    boolean isOpen;

    public TabsItem(int img, String title, boolean isOpen) {
        this.img = img;
        this.title = title;
        this.isOpen = isOpen;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
