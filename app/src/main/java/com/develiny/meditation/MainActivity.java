package com.develiny.meditation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.develiny.meditation.controller.AudioController;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.dialog.AddTitleDialog;
import com.develiny.meditation.notification.DefaultNofitication;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.FavPage;
import com.develiny.meditation.page.HzPage;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;
import com.develiny.meditation.page.adapter.TabsAdapter;
import com.develiny.meditation.page.item.PageItem;
import com.develiny.meditation.page.item.TabsItem;
import com.develiny.meditation.service.DownloadService;
import com.develiny.meditation.service.GetStateKillApp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView tabs;

    public static ViewPager viewPager;
    SectionsPagerAdapter sectionsPagerAdapter;
    AudioManager audioManager;
    public static int maxVolumn;

    Button testbtn0, testbtn, testbtn1;

    //bottom sheet
    RelativeLayout bottomSheetTitleBar;
    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout linearLayout;
    public static Button pands;
    Button upAndDown, deletePlayingList, addfav;
    public static RecyclerView bottomRecyclerView;
    public static BottomSheetAdapter bottomSheetAdapter;
    public static ArrayList<PageItem> playingList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGetStateKillApp();

        setAudioManager();

        setDatabaseHandler();
        setViewPager();
        setTabs();
        setButtonSheet();

        testbtn();
    }

    private void startGetStateKillApp() {
        startService(new Intent(MainActivity.this, GetStateKillApp.class));
    }

    private void setAudioManager() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolumn = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    private void setDatabaseHandler() {
        databaseHandler.setDB(MainActivity.this);
        databaseHandler = new DatabaseHandler(MainActivity.this);
    }

    private void setViewPager() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        FavPage favPage = new FavPage();
        sectionsPagerAdapter.addItem(favPage);
        Page1 page1 = new Page1();
        sectionsPagerAdapter.addItem(page1);
        Page2 page2 = new Page2();
        sectionsPagerAdapter.addItem(page2);
        ChakraPage chakraPage = new ChakraPage();
        sectionsPagerAdapter.addItem(chakraPage);
        HzPage hzPage = new HzPage();
        sectionsPagerAdapter.addItem(hzPage);
        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    private void testbtn() {
        testbtn0 = findViewById(R.id.testbtn0);
        testbtn = findViewById(R.id.testbtn);
        testbtn1 = findViewById(R.id.testbtn1);

        String path = getApplicationInfo().dataDir + "/cache/audio1-1";
        File file2 = new File(path);
//        File file = new File(path + "/audios");
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        testbtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DownloadService.class);
                if (DownloadService.isDownloadOpen) {
                    stopService(intent);
                } else {
                }
            }
        });

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference reference = storage.getReference();
                File localFile;
                try {
                    localFile = File.createTempFile("audio", "1-1");
                    reference.child("cde.wav").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.d("MainAcativity>>>", "success: " + localFile.getName());
                            File from = new File(getApplicationInfo().dataDir + "/cache", localFile.getName());
                            File to = new File(getApplicationInfo().dataDir + "/cache", "audio1-1");
                            if (from.exists()) {
                                from.renameTo(to);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("MainActivity>>>", "failed");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        testbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED) {
//
//                Rect outRect = new Rect();
//                bottomSheetTitleBar.getGlobalVisibleRect(outRect);
//
//                if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            }
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }

    private void setButtonSheet() {
        this.bottomSheetTitleBar = findViewById(R.id.bottom_sheet_title_bar);
        this.pands = findViewById(R.id.bottom_sheet_pands);
        this.upAndDown = findViewById(R.id.bottom_upanddown);
        this.addfav = findViewById(R.id.bottom_sheet_add_fav);
        this.deletePlayingList = findViewById(R.id.bottom_delete_playing_list);
        this.bottomRecyclerView = findViewById(R.id.bottom_recyclerview);
        linearLayout = findViewById(R.id.bottom_sheet_id);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int y = (int)(size.y * 0.4);
//        ViewGroup.LayoutParams params = bottomRecyclerView.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = y;
//        bottomRecyclerView.setLayoutParams(params);
        bottomRecyclerView.setMinimumHeight(y);

        bottomSheetTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    upAndDown.setBackgroundResource(R.drawable.bottom_up);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    upAndDown.setBackgroundResource(R.drawable.bottom_down);
                }
            }
        });

        pands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.playingList.size() == 0) {
                    Toast.makeText(MainActivity.this, "null play list", Toast.LENGTH_SHORT).show();
                } else {
                    checkOpenService();
                    if (AudioController.checkIsPlaying(playingList.get(0).getPnp())) {//재생중
                        Log.d("MainActivity>>>", "1");
                        pands.setBackgroundResource(R.drawable.bottom_play);
                        ArrayList<PageItem> page = new ArrayList<>();
                        for (int i = 0; i < MainActivity.playingList.size(); i++) {
                            page.add(MainActivity.playingList.get(i));
                            if (i == MainActivity.playingList.size() - 1) {
                                AudioController.stopPlayingList(page);
                                DefaultNofitication.defauleNotification(MainActivity.this);
                            }
                        }
                    } else {//재생중 아님
                        Log.d("MainActivity>>>", "2");
                        pands.setBackgroundResource(R.drawable.bottom_pause);
                        List<String> pp = new ArrayList<>();
                        for (int i = 0; i < MainActivity.playingList.size(); i++) {
                            pp.add(MainActivity.playingList.get(i).getPnp());
                            if (i == MainActivity.playingList.size() - 1) {
                                //playinglist start
                                AudioController.startPlayingList(MainActivity.this, pp);
                            }
                        }
                    }
                }
            }
        });

        upAndDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    upAndDown.setBackgroundResource(R.drawable.bottom_up);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    upAndDown.setBackgroundResource(R.drawable.bottom_down);
                }
            }
        });

        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playingList.size() != 0) {
                    AddTitleDialog.addTitleDialog(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "null playinglist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        playingList = databaseHandler.playingList();
        bottomSheetAdapter = new BottomSheetAdapter(playingList, MainActivity.this);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        bottomRecyclerView.setLayoutManager(layoutManager);
        bottomRecyclerView.setAdapter(bottomSheetAdapter);

        deletePlayingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NotificationService.isPlaying) {
                    Intent intent = new Intent(MainActivity.this, NotificationService.class);
                    stopService(intent);
                }
                if (playingList.size() != 0) {
                    databaseHandler.deleteAllPlayingListTest();
                }
            }
        });
    }

    private void checkOpenService() {
        if (!NotificationService.isPlaying) {
            Intent intent = new Intent(MainActivity.this, NotificationService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                MainActivity.this.startForegroundService(intent);
            } else {
                MainActivity.this.startService(intent);
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int i) {
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    private void setTabs() {
        tabs = findViewById(R.id.tablayout);
        ArrayList<TabsItem> tabsList = new ArrayList<>();
        tabsList.add(new TabsItem(R.drawable.bottom_play, "fav", false));
        tabsList.add(new TabsItem(R.drawable.bottom_pause, "rain", true));
        tabsList.add(new TabsItem(R.drawable.bottom_up, "wind", false));
        tabsList.add(new TabsItem(R.drawable.delete_playinglist, "chakra", false));
        tabsList.add(new TabsItem(R.drawable.playlist_play, "mandra", false));
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        tabs.setLayoutManager(layoutManager);
        TabsAdapter tabsAdapter =new TabsAdapter(tabsList, MainActivity.this);
        tabs.setAdapter(tabsAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tabsList.size(); i++) {
                    if (i != position) {
                        tabsList.get(i).setOpen(false);
                        tabsAdapter.notifyItemChanged(i);
                    } else {
                        tabsList.get(position).setOpen(true);
                        tabsAdapter.notifyItemChanged(position);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}