package com.develiny.meditation.controller;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.adapter.FavListAdapter;
import com.develiny.meditation.page.adapter.FavTitleAdapter;
import com.develiny.meditation.page.item.FavListItem;
import com.develiny.meditation.page.item.PageItem;

public class SeekController {

    public static DatabaseHandler databaseHandler;

    public final static int MAX_VOLUME = 16;

    public static boolean pageMoving;
    public static boolean bottomMoving;
    public static boolean favMoving;

    public static void changeVolumn(String pp, float volumn) {
        AudioController.playingListindex0_1(pp).setVolume(volumn, volumn);
        AudioController.playingListindex0_2(pp).setVolume(volumn, volumn);
    }

    public static void changeSeekInPage(Context context, PageItem pageItem, int progress) {
        int position = 0;
        if (pageItem.getPage() == 1) {
            position = Page1.arrayList.indexOf(pageItem);
        } else if (pageItem.getPage() == 2) {
            position = Page1.arrayList.indexOf(pageItem);
        }

        if (MainActivity.playingList.contains(pageItem)) {
            MainActivity.playingList.get(position).setSeek(progress);
            MainActivity.bottomSheetAdapter.notifyItemChanged(position);
            MainActivity.bottomSheetAdapter.notifyDataSetChanged();
        }

        databaseHandler = new DatabaseHandler(context);
        databaseHandler.changePageSeek(pageItem.getPage(), progress, pageItem.getPosition(), pageItem.getPnp());
    }

    public static void changeSeekInBottom(Context context, PageItem pageItem, int progress) {
        int position = pageItem.getPosition() - 1;
        if (pageItem.getPage() == 1) {
            Page1.arrayList.get(position).setSeek(progress);
            Page1.adapter.notifyItemChanged(position);
            Page1.adapter.notifyDataSetChanged();

            for (int i = 0; i < FavListAdapter.arrayList.size(); i++) {
                if (FavListAdapter.arrayList.get(i).getPnp().equals(pageItem.getPnp())) {
                    FavListAdapter.arrayList.get(i).setSeek(progress);
                    FavTitleAdapter.favListAdapter.notifyItemChanged(i);
                    FavTitleAdapter.favListAdapter.notifyDataSetChanged();
                }
            }

        } else if (pageItem.getPage() == 2) {
            Page2.arrayList.get(position).setSeek(progress);
            Page2.adapter.notifyItemChanged(position);
            Page2.adapter.notifyDataSetChanged();
        }
        databaseHandler = new DatabaseHandler(context);
        databaseHandler.changePageSeek(pageItem.getPage(), progress, pageItem.getPosition(), pageItem.getPnp());
    }

    public static void changeSeekInFavList(Context context, FavListItem favListItem, int progress) {

    }
}
