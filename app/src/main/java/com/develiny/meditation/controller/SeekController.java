package com.develiny.meditation.controller;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;
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
        if (AudioController.playingListindex0_1(pp) != null) {
            AudioController.playingListindex0_1(pp).setVolume(volumn, volumn);
            AudioController.playingListindex0_2(pp).setVolume(volumn, volumn);
        }
    }

    public static void changeSeekInPage(Context context, PageItem pageItem, int progress) {

        if (MainActivity.playingList.size() != 0 ) {
            for (int i = 0; i < MainActivity.playingList.size(); i++) {
                if (MainActivity.playingList.get(i).getPnp().equals(pageItem.getPnp())) {
                    MainActivity.playingList.get(i).setSeek(progress);
                    MainActivity.bottomSheetAdapter.notifyItemChanged(i);
                    MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }

        for (int i = 0; i < FavListAdapter.arrayList.size(); i++) {
            if (FavListAdapter.arrayList.get(i).getPnp().equals(pageItem.getPnp())) {
                FavListAdapter.arrayList.get(i).setSeek(progress);
                FavTitleAdapter.favListAdapter.notifyItemChanged(i);
                FavTitleAdapter.favListAdapter.notifyDataSetChanged();
                break;
            }
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
        } else if (pageItem.getPage() == 2) {
            Page2.arrayList.get(position).setSeek(progress);
            Page2.adapter.notifyItemChanged(position);
            Page2.adapter.notifyDataSetChanged();
        } else if (pageItem.getPage() == 3) {
            ChakraPage.arrayList.get(position).setSeek(progress);
            ChakraPage.adapter.notifyItemChanged(position);
            ChakraPage.adapter.notifyDataSetChanged();
        }

        for (int i = 0; i < FavListAdapter.arrayList.size(); i++) {
            if (FavListAdapter.arrayList.get(i).getPnp().equals(pageItem.getPnp())) {
                FavListAdapter.arrayList.get(i).setSeek(progress);
                FavTitleAdapter.favListAdapter.notifyItemChanged(i);
                FavTitleAdapter.favListAdapter.notifyDataSetChanged();
            }
        }
        databaseHandler = new DatabaseHandler(context);
        databaseHandler.changePageSeek(pageItem.getPage(), progress, pageItem.getPosition(), pageItem.getPnp());
    }

    public static void changeSeekInFavList(Context context, FavListItem favListItem, int progress) {
        int position = favListItem.getPosition() - 1;
        if (favListItem.getPage() == 1) {
            Page1.arrayList.get(position).setSeek(progress);
            Page1.adapter.notifyItemChanged(position);
            Page1.adapter.notifyDataSetChanged();
        } else if (favListItem.getPage() == 2) {
            Page2.arrayList.get(position).setSeek(progress);
            Page2.adapter.notifyItemChanged(position);
            Page2.adapter.notifyDataSetChanged();
        }

        if (MainActivity.playingList.size() != 0) {
            for (int i = 0; i < MainActivity.playingList.size(); i++) {
                if (MainActivity.playingList.get(i).getPnp().equals(favListItem.getPnp())) {
                    MainActivity.playingList.get(i).setSeek(progress);
                    MainActivity.bottomSheetAdapter.notifyItemChanged(i);
                    MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }

        databaseHandler = new DatabaseHandler(context);
        databaseHandler.changePageSeek(favListItem.getPage(), progress, favListItem.getPosition(), favListItem.getPnp());
    }
}
