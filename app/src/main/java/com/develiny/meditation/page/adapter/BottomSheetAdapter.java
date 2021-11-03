package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.controller.SeekController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.CustomViewHolder> {

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
        int positions = position;
        Bitmap bitmap = BitmapFactory.decodeByteArray(arrayList.get(position).getImg(), 0, arrayList.get(position).getImg().length);
        holder.button.setImageBitmap(bitmap);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(">>>BottomSheetAdapter", "getposition: " + positions);
            }
        });
        holder.seekBar.setProgress(arrayList.get(position).getSeek());
        holder.seekBar.setMax(MainActivity.maxVolumn);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int getposition = arrayList.get(positions).getPosition();
                int getpage = arrayList.get(positions).getPage();
                int index = arrayList.indexOf(arrayList.get(positions));
                databaseHandler.deletePlayingList(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                AudioController.stopPage(arrayList.get(positions).getPage());
//                MainActivity.playingList.remove(index);
                arrayList.remove(index);
                MainActivity.bottomSheetAdapter.notifyItemRemoved(index);
                MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                if (MainActivity.playingList.size() == 0) {
                    stopServiceWhenPlaylistZero(context);
                }
                changePageItemBackground(getpage, getposition);
                Log.d(">>>", "size: " + arrayList.size());
            }
        });

        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (SeekController.bottomMoving) {
                    float volume = (float) (1 - (Math.log(SeekController.MAX_VOLUME - i) / Math.log(SeekController.MAX_VOLUME)));
                    String pp = arrayList.get(positions).getPnp();
                    SeekController.changeVolumn(pp, volume);
                    SeekController.changeSeekInBottom(context, arrayList.get(positions), seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekController.bottomMoving = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekController.bottomMoving = false;
            }
        });
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

    private void stopServiceWhenPlaylistZero(Context context) {
        if (MainActivity.playingList.size() == 0) {
            MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
            if (NotificationService.isPlaying) {
                context.stopService(new Intent(context, NotificationService.class));
            }
        }
    }

    private void changePageItemBackground(int page, int position) {
        if (page == 1) {
            Page1.arrayList.get(position - 1).setIsplay(1);
            Page1.adapter.notifyItemChanged(position - 1);
//            Page1.adapter.notifyDataSetChanged();
        } else if (page == 2) {
            Page2.arrayList.get(position - 1).setIsplay(1);
            Page2.adapter.notifyItemChanged(position - 1);
//            Page2.adapter.notifyDataSetChanged();
        } else if (page == 3) {
            ChakraPage.arrayList.get(position - 1).setIsplay(1);
            ChakraPage.adapter.notifyItemChanged(position - 1);
        } else if (page == 4) {
            HzPage.arrayList.get(position - 1).setIsplay(1);
            HzPage.adapter.notifyItemChanged(position - 1);
        }
    }

    private void changeSeekChange() {

    }
}
