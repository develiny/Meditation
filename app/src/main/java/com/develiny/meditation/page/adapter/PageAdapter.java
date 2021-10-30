package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.audiocontroller.AudioController;
import com.develiny.meditation.audiocontroller.P1Controller;
import com.develiny.meditation.audiocontroller.P2Controller;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.notification.DefaultNofitication;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.CustomViewHolder> {

    ArrayList<PageItem> arrayList;
    Context context;
    DatabaseHandler databaseHandler;

    private final static int MAX_VOLUME = 16;

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
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(arrayList.get(position).getImgdefault(), 0, arrayList.get(position).getImgdefault().length);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(arrayList.get(position).getImg(), 0, arrayList.get(position).getImg().length);
        if (arrayList.get(position).getIsplay() == 1) {
            holder.button.setImageBitmap(bitmap1);
        } else {
            holder.button.setImageBitmap(bitmap2);
        }
        holder.seekBar.setProgress(arrayList.get(position).getSeek());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler = new DatabaseHandler(context);
                if (arrayList.get(positions).getIsplay() == 1) {
                    Bitmap bitmapremove = BitmapFactory.decodeByteArray(arrayList.get(positions).getImg(), 0, arrayList.get(positions).getImg().length);
                    holder.button.setImageBitmap(bitmapremove);
                    for(int i = 0; i < arrayList.size(); i++) {
                        int isplay = arrayList.get(i).getIsplay();
                        if (isplay == 2) {//change
                            int index = checkPlayinglistPosition(arrayList.get(i).getPage());
                            Log.d(">>>PageAdapter", "index: " + index);
                            MainActivity.playingList.remove(index);
                            MainActivity.bottomSheetAdapter.notifyItemRemoved(index);
                            arrayList.get(i).setIsplay(1);
                            notifyItemChanged(i);
                            notifyDataSetChanged();
                            AudioController.stopPage(arrayList.get(positions).getPage());
                            break;
                        }
                    }
                    //add
                    MainActivity.pands.setBackgroundResource(R.drawable.bottom_pause);
                    arrayList.get(positions).setIsplay(2);
                    MainActivity.playingList.add(arrayList.get(positions));
                    databaseHandler.setPlay1(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                    MainActivity.bottomSheetAdapter.notifyItemInserted(MainActivity.playingList.size());
                    AudioController.startTrack(context, arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                    checkOpenService();
                    Log.d(">>>PageAdapter", "size: " + MainActivity.playingList.size());
                } else {
                    //remove
                    Bitmap bitmapadd = BitmapFactory.decodeByteArray(arrayList.get(positions).getImgdefault(), 0, arrayList.get(positions).getImgdefault().length);
                    holder.button.setImageBitmap(bitmapadd);
                    databaseHandler.deletePlayingList(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                    for(int i = 0; i < MainActivity.playingList.size(); i++) {
                        if (MainActivity.playingList.get(i).getPnp().equals(arrayList.get(positions).getPnp())) {
                            MainActivity.playingList.remove(i);
                            MainActivity.bottomSheetAdapter.notifyItemRemoved(i);
                            MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    arrayList.get(positions).setIsplay(1);
                    AudioController.stopPage(arrayList.get(positions).getPage());
                    stopServiceWhenPlaylistZero(context);
                }
            }
        });

        holder.seekBar.setMax(MainActivity.maxVolumn);
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float volume = (float) (1 - (Math.log(MAX_VOLUME - i) / Math.log(MAX_VOLUME)));
                String pp = arrayList.get(positions).getPnp();
                changeVolumn(pp, volume);
//                databaseHandler.updateVolumn(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                Log.d(">>>1", "onProgressChanged");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(">>>2", "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(">>>3", "onStopTrackingTouch");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView button;
        SeekBar seekBar;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.button = itemView.findViewById(R.id.page_item_toggle_button);
            this.seekBar = itemView.findViewById(R.id.page_item_seekbar);
        }
    }

    private void checkOpenService() {
        if (!NotificationService.isPlaying) {
            Intent intent = new Intent(context, NotificationService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }

    private void stopServiceWhenPlaylistZero(Context context) {
        if (MainActivity.playingList.size() == 0){
            MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
            if (NotificationService.isPlaying) {
                context.stopService(new Intent(context, NotificationService.class));
            }
        }
    }

    private void changeVolumn(String pp, float volumn) {
        AudioController.playingListindex0_1(pp).setVolume(volumn, volumn);
        AudioController.playingListindex0_2(pp).setVolume(volumn, volumn);
    }

    private int checkPlayinglistPosition(int page) {
        for (int i = 0; i < MainActivity.playingList.size(); i++) {
            int playlistpage = MainActivity.playingList.get(i).getPage();
            if (playlistpage == page) {
                return i;
            }
        }
        return -1;
    }
}
