package com.develiny.meditation.page;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.adapter.PageAdapter;
import com.develiny.meditation.page.adapter.StoragePageAdapter;
import com.develiny.meditation.page.item.PageItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HzPage extends Fragment {

    public static MediaPlayer p4p1, p4p2;

    RecyclerView recyclerView;
    public static StoragePageAdapter adapter;
    public static ArrayList<PageItem> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;

    public static RelativeLayout load;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);

        setAudio(getActivity());

        setInit(rootView);
        setDatabaseHandler();
        setRecyclerView();

        return rootView;
    }

    public static void setAudio(Context context) {
        p4p1 = new MediaPlayer();
        p4p2 = new MediaPlayer();
        String path4_1 = context.getApplicationInfo().dataDir + "/cache/audio4-1.mp3";
        String path4_2 = context.getApplicationInfo().dataDir + "/cache/audio4-2.mp3";
        setDataSourceAudio(p4p1, path4_1);
        setDataSourceAudio(p4p2, path4_2);
    }

    private static void setDataSourceAudio(MediaPlayer mp, String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                mp.setDataSource(path);
                mp.setLooping(true);
                mp.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setInit(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.page_recyclerview);
    }

    private void setDatabaseHandler() {
        databaseHandler.setDB(getActivity());
        databaseHandler = new DatabaseHandler(getActivity());
    }

    private void setRecyclerView() {
        arrayList = databaseHandler.hzList();
        adapter = new StoragePageAdapter(arrayList, getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        setHzVolumn();
    }

    public static void setHzVolumn() {
        AudioController.setVolumn("4-1", arrayList.get(0).getSeek());
        AudioController.setVolumn("4-2", arrayList.get(1).getSeek());
    }
}
