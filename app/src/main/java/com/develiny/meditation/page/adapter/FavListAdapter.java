package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.item.FavListItem;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.CustomViewHolder> {
    String title;
    Context context;
    ArrayList<FavListItem> arrayList = new ArrayList<>();
    DatabaseHandler databaseHandler;

    public FavListAdapter(String title, Context context) {
        this.title = title;
        this.context = context;
    }

    @NonNull
    @Override
    public FavListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playing_item, parent, false);
        FavListAdapter.CustomViewHolder holder = new FavListAdapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavListAdapter.CustomViewHolder holder, int position) {

        databaseHandler = new DatabaseHandler(context);
        arrayList = databaseHandler.getFavListItem(title);

        Bitmap bitmap1 = BitmapFactory.decodeByteArray(arrayList.get(position).getImgdefault(), 0, arrayList.get(position).getImgdefault().length);
        holder.button.setImageBitmap(bitmap1);
        holder.seekBar.setProgress(arrayList.get(position).getSeek());
    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView button;
        SeekBar seekBar;
        Button btn;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.button = itemView.findViewById(R.id.playing_item_img);
            this.seekBar = itemView.findViewById(R.id.playing_item_seekbar);
            this.btn = itemView.findViewById(R.id.playing_item_btn);
        }
    }
}
