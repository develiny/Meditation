package com.develiny.meditation.page.item;

public class PageItem {
    int position;
    byte[] img;
    int seek;

    public PageItem() {

    }

    public PageItem(int position, byte[] img, int seek) {
        this.position = position;
        this.img = img;
        this.seek = seek;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getSeek() {
        return seek;
    }

    public void setSeek(int seek) {
        this.seek = seek;
    }
}
