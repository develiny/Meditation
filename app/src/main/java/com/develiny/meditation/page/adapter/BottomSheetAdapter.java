package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.CustomViewHolder>{

    Context context;
    ArrayList<PageItem> arrayList;
    DatabaseHandler databaseHandler;
    private SQLiteDatabase sqLiteDatabase;

    public BottomSheetAdapter(ArrayList<PageItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playing_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        databaseHandler.setDB(context);
        databaseHandler = new DatabaseHandler(context);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(arrayList.get(position).getImg(), 0, arrayList.get(position).getImg().length);
        holder.button.setImageBitmap(bitmap);
        holder.seekBar.setProgress(arrayList.get(position).getSeek());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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
