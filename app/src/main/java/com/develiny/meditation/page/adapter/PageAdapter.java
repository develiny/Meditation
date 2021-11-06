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
import com.develiny.meditation.page.item.PageItem;

import java.util.ArrayList;
import java.util.List;

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
                if (arrayList.get(positions).getIsplay() == 1) {//해당 아이템이 playing중이 아닐때
                    Bitmap bitmapremove = BitmapFactory.decodeByteArray(arrayList.get(positions).getImg(), 0, arrayList.get(positions).getImg().length);
                    holder.button.setImageBitmap(bitmapremove);
                    for (int i = 0; i < arrayList.size(); i++) {//같은 page에 재생중인게 있으면 없애기
                        int isplay = arrayList.get(i).getIsplay();
                        if (isplay == 2) {//change
                            int index = checkPlayinglistPosition(arrayList.get(i).getPage());
                            Log.d(">>>PageAdapter", "index: " + index);
                            MainActivity.playingList.remove(index);
                            MainActivity.bottomSheetAdapter.notifyItemRemoved(index);
                            arrayList.get(i).setIsplay(1);
                            notifyItemChanged(i);
                            notifyDataSetChanged();
                            AudioController.stopPage(arrayList.get(positions).getPage(), arrayList.get(positions).getPnp());
                            break;
                        }
                    }
                    //add
                    MainActivity.pands.setBackgroundResource(R.drawable.bottom_pause);
                    arrayList.get(positions).setIsplay(2);
                    MainActivity.playingList.add(arrayList.get(positions));
                    databaseHandler.setPlay1(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                    MainActivity.bottomSheetAdapter.notifyItemInserted(MainActivity.playingList.size());
                    for (int ii = 0; ii < MainActivity.playingList.size(); ii++) {
                        List<String> pp = new ArrayList<>();
                        pp.add(MainActivity.playingList.get(ii).getPnp());
                        if (ii == MainActivity.playingList.size() - 1) {
                            AudioController.startPlayingList(context, pp);
                        }
                    }
                    checkOpenService();
                    Log.d(">>>PageAdapter", "size: " + MainActivity.playingList.size());
                } else {//해당 아이템이 playing중일때
                    //remove
                    Bitmap bitmapadd = BitmapFactory.decodeByteArray(arrayList.get(positions).getImgdefault(), 0, arrayList.get(positions).getImgdefault().length);
                    holder.button.setImageBitmap(bitmapadd);
                    databaseHandler.deletePlayingList(arrayList.get(positions).getPage(), arrayList.get(positions).getPosition());
                    for (int i = 0; i < MainActivity.playingList.size(); i++) {
                        if (MainActivity.playingList.get(i).getPnp().equals(arrayList.get(positions).getPnp())) {
                            MainActivity.playingList.remove(i);
                            MainActivity.bottomSheetAdapter.notifyItemRemoved(i);
                            MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    arrayList.get(positions).setIsplay(1);
                    AudioController.stopPage(arrayList.get(positions).getPage(), arrayList.get(positions).getPnp());
                    stopServiceWhenPlaylistZero(context);
                }
            }
        });

        holder.seekBar.setMax(MainActivity.maxVolumn);
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//터치
                SeekController.pageMoving = true;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//변화
                if (SeekController.pageMoving) {
                    float volume = (float) (1 - (Math.log(SeekController.MAX_VOLUME - i) / Math.log(SeekController.MAX_VOLUME)));
                    String pp = arrayList.get(positions).getPnp();
                    SeekController.changeVolumn(pp, volume);
                    SeekController.changeSeekInPage(context, arrayList.get(positions), seekBar.getProgress());
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//끝
                SeekController.pageMoving = false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView button;
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
        if (MainActivity.playingList.size() == 0) {
            MainActivity.pands.setBackgroundResource(R.drawable.bottom_play);
            if (NotificationService.isPlaying) {
                context.stopService(new Intent(context, NotificationService.class));
            }
        }
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
