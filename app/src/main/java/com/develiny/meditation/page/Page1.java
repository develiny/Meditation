package com.develiny.meditation.page;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.adapter.PageAdapter;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class Page1 extends Fragment {

    public static MediaPlayer p1p1_1, p1p1_2;
    public static MediaPlayer p1p2_1, p1p2_2;

    RecyclerView recyclerView;
    public static PageAdapter adapter;
    public static ArrayList<PageItem> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page1, container, false);

        setAudio();

        setInit(rootView);
        setDatabaseHandler();
        setRecyclerView();

        return rootView;
    }

    private void setAudio() {
        p1p1_1 = MediaPlayer.create(getActivity(), R.raw.p1p1);
        p1p1_2 = MediaPlayer.create(getActivity(), R.raw.p1p1);
        p1p2_1 = MediaPlayer.create(getActivity(), R.raw.p1p2);
        p1p2_2 = MediaPlayer.create(getActivity(), R.raw.p1p2);
    }

    private void setInit(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.page1_recyclerview);
    }

    private void setDatabaseHandler() {
        databaseHandler.setDB(getActivity());
        databaseHandler = new DatabaseHandler(getActivity());
    }

    private void setRecyclerView() {
        arrayList = databaseHandler.rainList();
        adapter = new PageAdapter(arrayList, getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setPage1Volumn();
    }

    private void setPage1Volumn() {
        AudioController.setVolumn("1-1", arrayList.get(0).getSeek());
        AudioController.setVolumn("1-2", arrayList.get(1).getSeek());
    }
}
