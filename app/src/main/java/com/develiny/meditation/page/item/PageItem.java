package com.develiny.meditation.page.item;

public class PageItem {
    int page;
    int position;
    byte[] img;
    int seek;
    int isplay;

    public PageItem() {

    }

    public PageItem(int page, int position, byte[] img, int seek, int isplay) {
        this.page = page;
        this.position = position;
        this.img = img;
        this.seek = seek;
        this.isplay = isplay;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getIsplay() {
        return isplay;
    }

    public void setIsplay(int isplay) {
        this.isplay = isplay;
    }
}
