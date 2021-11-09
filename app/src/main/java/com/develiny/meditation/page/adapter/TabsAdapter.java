package com.develiny.meditation.page.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.page.item.TabsItem;

import java.util.ArrayList;

public class TabsAdapter extends RecyclerView.Adapter<TabsAdapter.CustomViewHolder> {
    ArrayList<TabsItem> arrayList;
    Activity activity;

    public TabsAdapter(ArrayList<TabsItem> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TabsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TabsAdapter.CustomViewHolder holder, int position) {
        int pos = position;
        holder.img.setImageResource(arrayList.get(pos).getImg());
        holder.title.setText(arrayList.get(pos).getTitle());
        if (arrayList.get(position).isOpen()) {
            holder.outline.setBackgroundResource(R.drawable.tab_bg_choice);
            holder.title.setVisibility(View.VISIBLE);
        } else {
            holder.outline.setBackgroundResource(R.drawable.tab_bg_default);
            holder.title.setVisibility(View.GONE);
        }

        holder.outline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.viewPager.setCurrentItem(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        LinearLayout outline;
        ImageView img;
        TextView title;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            outline = itemView.findViewById(R.id.tab_item_outline);
            img = itemView.findViewById(R.id.tab_item_imageview);
            title = itemView.findViewById(R.id.tab_item_text);
        }
    }
}
