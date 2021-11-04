package com.develiny.meditation.page.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.dialog.DeleteFavTitleDialog;
import com.develiny.meditation.dialog.EditFavTitleDialog;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.item.FavListItem;
import com.develiny.meditation.page.item.FavTitleItem;

import java.util.ArrayList;

public class FavTitleAdapter extends RecyclerView.Adapter<FavTitleAdapter.CustomViewHolder>{

    ArrayList<FavTitleItem> arrayList;
    Context context;
    DatabaseHandler databaseHandler;
    RecyclerView.LayoutManager layoutManager;
    public static FavListAdapter favListAdapter;

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

        holder.title.setText(arrayList.get(position).getTitle());

        if (arrayList.get(i).getIsopen() == 1) {
            holder.recyclerView.setVisibility(View.GONE);
            holder.uandd.setChecked(false);
            holder.uandd.setBackgroundResource(R.drawable.bottom_down);
        } else {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.uandd.setChecked(true);
            holder.uandd.setBackgroundResource(R.drawable.bottom_up);
        }

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler = new DatabaseHandler(context);
                if (MainActivity.playingList.size() != 0) {//만약 playinglist에 재생목록이 있다면
                    ArrayList<Integer> pagelist = new ArrayList<>();
                    ArrayList<Integer> positionlist = new ArrayList<>();
                    for (int ii = 0; ii < MainActivity.playingList.size(); ii++) {
                        pagelist.add(MainActivity.playingList.get(ii).getPage());
                        positionlist.add(MainActivity.playingList.get(ii).getPosition());
                        AudioController.stopPage(MainActivity.playingList.get(ii).getPage(), MainActivity.playingList.get(ii).getPnp());
                        if (ii == MainActivity.playingList.size() - 1) {
                            MainActivity.playingList.clear();
                            MainActivity.bottomSheetAdapter.notifyItemRangeRemoved(0, MainActivity.playingList.size() - 1);
                            MainActivity.bottomSheetAdapter.notifyDataSetChanged();
                            databaseHandler.deleteAllPlayinglist(pagelist, positionlist, arrayList.get(i).getTitle());
                        }
                    }
                } else {//playinglist에 기존목록 없다면
                    databaseHandler.addFavListInPlayinglist(arrayList.get(i).getTitle());
                }
                ArrayList<String> pnplist = new ArrayList<>();
                for (int i = 0; i < MainActivity.playingList.size(); i++) {
                    pnplist.add(MainActivity.playingList.get(i).getPnp());
                    changePageImage(MainActivity.playingList.get(i).getPage(), MainActivity.playingList.get(i).getPosition() - 1);
                    if (i == MainActivity.playingList.size() - 1) {
                        AudioController.startPlayingList(context, pnplist);
                        AudioController.checkOpenService(context);
                        MainActivity.pands.setBackgroundResource(R.drawable.bottom_pause);
                    }
                }
                AudioController.removeLoad();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFavTitleDialog.editFavTitleDialog(context, arrayList.get(i).getTitle(), i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteFavTitleDialog.deleteFavTitleDialog(context, arrayList.get(i).getTitle());
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHandler = new DatabaseHandler(context);
                if (arrayList.get(i).getIsopen() == 1) {
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());
                    databaseHandler.changeIsOpen(arrayList.get(i).getIsopen(), arrayList.get(i).getTitle());

                    for (int ii = 0; ii < arrayList.size(); ii++) {
                        if (arrayList.get(ii).getIsopen() == 2) {
                            arrayList.get(ii).setIsopen(1);
                        }
                    }
                    arrayList.get(i).setIsopen(2);

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);
                } else {
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());
                    databaseHandler.changeIsOpen(arrayList.get(i).getIsopen(), arrayList.get(i).getTitle());

                    for (int ii = 0; ii < arrayList.size(); ii++) {
                        if (arrayList.get(ii).getIsopen() == 2) {
                            arrayList.get(ii).setIsopen(1);
                        }
                    }

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);
                }


                notifyItemRangeChanged(0, arrayList.size() - 1);
                notifyDataSetChanged();
            }
        });

        holder.uandd.setChecked(false);
        holder.uandd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHandler = new DatabaseHandler(context);
                if (arrayList.get(i).getIsopen() == 1) {
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());
                    databaseHandler.changeIsOpen(arrayList.get(i).getIsopen(), arrayList.get(i).getTitle());

                    for (int ii = 0; ii < arrayList.size(); ii++) {
                        if (arrayList.get(ii).getIsopen() == 2) {
                            arrayList.get(ii).setIsopen(1);
                        }
                    }
                    arrayList.get(i).setIsopen(2);

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);
                } else {
                    ArrayList<FavListItem> favListItemArrayList;
                    favListItemArrayList = databaseHandler.getFavListItem(arrayList.get(i).getTitle());
                    databaseHandler.changeIsOpen(arrayList.get(i).getIsopen(), arrayList.get(i).getTitle());

                    for (int ii = 0; ii < arrayList.size(); ii++) {
                        if (arrayList.get(ii).getIsopen() == 2) {
                            arrayList.get(ii).setIsopen(1);
                        }
                    }

                    favListAdapter = new FavListAdapter(favListItemArrayList, context);
                    layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setHasFixedSize(true);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(favListAdapter);
                }


                notifyItemRangeChanged(0, arrayList.size() - 1);
                notifyDataSetChanged();
            }
        });

//        holder.uandd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
////                    holder.uandd.setBackgroundResource(R.drawable.bottom_up);
//                    holder.uandd.setBackgroundResource(R.drawable.bottom_down);
//                } else {
//                    holder.uandd.setBackgroundResource(R.drawable.bottom_up);
////                    holder.uandd.setBackgroundResource(R.drawable.bottom_down);
//                }
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        Button play, edit, delete;
        CheckBox uandd;
        RecyclerView recyclerView;
        LinearLayout linearLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.fav_page_item_title);
            this.play = itemView.findViewById(R.id.fav_page_item_play);
            this.edit = itemView.findViewById(R.id.fav_page_item_edit);
            this.delete = itemView.findViewById(R.id.fav_page_item_delete);
            this.uandd = itemView.findViewById(R.id.fav_page_item_uandd);
            this.recyclerView = itemView.findViewById(R.id.fav_page_inside_recyclerview);
            this.linearLayout = itemView.findViewById(R.id.fav_page_item_linear);
            this.recyclerView.setVisibility(View.GONE);
            if (this.uandd.isChecked()) {
                this.recyclerView.setVisibility(View.VISIBLE);
            } else {
                this.recyclerView.setVisibility(View.GONE);
            }
        }
    }

    private void changePageImage(int page, int position) {
        if (page == 1) {
            Page1.arrayList.get(position).setIsplay(2);
            Page1.adapter.notifyItemChanged(position);
            Page1.adapter.notifyDataSetChanged();
        } else if (page == 2) {
            Page2.arrayList.get(position).setIsplay(2);
            Page2.adapter.notifyItemChanged(position);
            Page2.adapter.notifyDataSetChanged();
        }
    }
}
