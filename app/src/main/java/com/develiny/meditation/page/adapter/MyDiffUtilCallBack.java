package com.develiny.meditation.page.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class MyDiffUtilCallBack extends DiffUtil.Callback {

    ArrayList<PageItem> newList;
    ArrayList<PageItem> oldList;

    public MyDiffUtilCallBack(ArrayList<PageItem> newList, ArrayList<PageItem> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getPage() == newList.get(newItemPosition).getPage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final PageItem oldPageItem = oldList.get(oldItemPosition);
        final PageItem newPageItem = newList.get(newItemPosition);

        return oldPageItem.getPage() == newPageItem.getPage();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}
