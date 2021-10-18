package com.develiny.meditation.page;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.develiny.meditation.page.adapter.PageAdapter;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class Page1 extends Fragment {

    RecyclerView recyclerView;
    ArrayList<PageItem> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page1, container, false);

        setInit(rootView);
        setDatabaseHandler();
        setRecyclerView();

        return rootView;
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
        PageAdapter adapter = new PageAdapter(arrayList, getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
