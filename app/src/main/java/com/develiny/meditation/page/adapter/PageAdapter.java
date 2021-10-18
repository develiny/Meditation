package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.CustomViewHolder> {

    ArrayList<PageItem> arrayList;
    Context context;
    DatabaseHandler databaseHandler;

    public PageAdapter(ArrayList<PageItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int positions = position;
        Bitmap bitmap = BitmapFactory.decodeByteArray(arrayList.get(position).getImgdefault(), 0, arrayList.get(position).getImgdefault().length);
        holder.button.setImageBitmap(bitmap);
        holder.seekBar.setProgress(arrayList.get(position).getSeek());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.get(positions).getIsplay() == 1) {
                    databaseHandler = new DatabaseHandler(context);
                    databaseHandler.setPlay3(arrayList.get(positions).getPage(),
                            arrayList.get(positions).getPosition());
                }
//                byte[] b = arrayList.get(positions).getImgdefault();
//                Log.d(">>>", "position: " + b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView button;
        SeekBar seekBar;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.button = itemView.findViewById(R.id.page_item_toggle_button);
            this.seekBar = itemView.findViewById(R.id.page_item_seekbar);
        }
    }
}
