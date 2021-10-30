package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.item.FavListItem;
import com.develiny.meditation.page.item.FavTitleItem;

import java.util.ArrayList;

public class FavTitleAdapter extends RecyclerView.Adapter<FavTitleAdapter.CustomViewHolder>{

    ArrayList<FavTitleItem> arrayList;
    Context context;
    DatabaseHandler databaseHandler;
    RecyclerView.LayoutManager layoutManager;
    FavListAdapter favListAdapter;

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

        if (arrayList.get(i).getIsplay() == 1) {
            holder.pands.setBackgroundResource(R.drawable.bottom_play);
        } else {
            holder.pands.setBackgroundResource(R.drawable.bottom_pause);
        }

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

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(">>>FavTitleAdapter", "onClick");
                if (holder.recyclerView.getVisibility() == View.GONE) {
                    databaseHandler = new DatabaseHandler(context);
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);

                    holder.uandd.setBackgroundResource(R.drawable.bottom_up);
                    holder.uandd.setChecked(true);
                    holder.recyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.uandd.setBackgroundResource(R.drawable.bottom_down);
                    holder.uandd.setChecked(true);
                    holder.recyclerView.setVisibility(View.GONE);
                }
            }
        });

        holder.uandd.setChecked(false);
        holder.uandd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.uandd.isChecked()) {
                    databaseHandler = new DatabaseHandler(context);
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);

                    holder.uandd.setBackgroundResource(R.drawable.bottom_up);
                    holder.recyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.uandd.setBackgroundResource(R.drawable.bottom_down);
                    holder.recyclerView.setVisibility(View.GONE);
                }
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
        CheckBox uandd;
        RecyclerView recyclerView;
        LinearLayout linearLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.fav_page_item_title);
            this.pands = itemView.findViewById(R.id.fav_page_item_pands);
            this.edit = itemView.findViewById(R.id.fav_page_item_edit);
            this.delete = itemView.findViewById(R.id.fav_page_item_delete);
            this.uandd = itemView.findViewById(R.id.fav_page_item_uandd);
            this.recyclerView = itemView.findViewById(R.id.fav_page_inside_recyclerview);
            this.linearLayout = itemView.findViewById(R.id.fav_page_item_linear);
            this.recyclerView.setVisibility(View.GONE);
        }
    }
}
