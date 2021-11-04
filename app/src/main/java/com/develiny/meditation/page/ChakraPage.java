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
import com.develiny.meditation.page.adapter.StorageAdapter;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class ChakraPage extends Fragment {

    public static MediaPlayer p3p1, p3p2;

    RecyclerView recyclerView;
    public static StorageAdapter adapter;
    public static ArrayList<PageItem> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page1, container, false);

//        setAudio();

        setInit(rootView);
        setDatabaseHandler();
        setRecyclerView();

        return rootView;
    }

    private void setAudio() {
        p3p1 = new MediaPlayer();
        p3p2 = new MediaPlayer();

    }

    private void setInit(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.page1_recyclerview);
    }

    private void setDatabaseHandler() {
        databaseHandler.setDB(getActivity());
        databaseHandler = new DatabaseHandler(getActivity());
    }

    private void setRecyclerView() {
        arrayList = databaseHandler.chakraList();
        adapter = new StorageAdapter(arrayList, getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setChakraVolumn();
    }

    private void setChakraVolumn() {
        AudioController.setVolumn("1-1", arrayList.get(0).getSeek());
        AudioController.setVolumn("1-2", arrayList.get(1).getSeek());
    }
}
