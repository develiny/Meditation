package com.develiny.meditation.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;

public class FavPage extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fav_page, container, false);

        setInit(rootView);
        setRecyclerView();

        return rootView;
    }

    private void setInit(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.fav_page_recyclerview);
    }

    private void setRecyclerView() {

    }
}
