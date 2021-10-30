package com.develiny.meditation.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.adapter.FavTitleAdapter;
import com.develiny.meditation.page.item.FavTitleItem;

import java.util.ArrayList;

public class FavPage extends Fragment {

    public static ArrayList<FavTitleItem> favTitleItemArrayList = new ArrayList<>();
    public static FavTitleAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fav_page, container, false);

        setInit(rootView);
        setDatabaseHandler();
        setRecyclerView();

        return rootView;
    }

    private void setInit(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.fav_page_recyclerview);
    }

    private void setDatabaseHandler() {
        databaseHandler.setDB(getActivity());
        databaseHandler = new DatabaseHandler(getActivity());
    }

    private void setRecyclerView() {
        favTitleItemArrayList = databaseHandler.favTitleList();
        adapter = new FavTitleAdapter(favTitleItemArrayList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
