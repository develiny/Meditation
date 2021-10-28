package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.item.FavTitleItem;

import java.util.ArrayList;

public class FavTitleAdapter extends RecyclerView.Adapter<FavTitleAdapter.CustomViewHolder>{

    ArrayList<FavTitleItem> arrayList;
    Context context;
    DatabaseHandler databaseHandler;

    public FavTitleAdapter(ArrayList<FavTitleItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavTitleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_page_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavTitleAdapter.CustomViewHolder holder, int position) {
        int i = position;
//        if (arrayList.get(position).getTitle() != null) {
//            holder.title.setText(arrayList.get(position).getIndexx());
//        }

        holder.title.setText(arrayList.get(position).getTitle());

        holder.pands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler = new DatabaseHandler(context);
                databaseHandler.removeFavList(arrayList.get(i).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        Button pands, edit, delete;
        RecyclerView recyclerView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.fav_page_item_title);
            this.pands = itemView.findViewById(R.id.fav_page_item_pands);
            this.edit = itemView.findViewById(R.id.fav_page_item_edit);
            this.delete = itemView.findViewById(R.id.fav_page_item_delete);
            this.recyclerView = itemView.findViewById(R.id.fav_page_inside_recyclerview);
        }
    }
}
