package com.develiny.meditation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    MediaPlayer mp1;

    //bottom sheet
    RelativeLayout bottomSheetTitleBar;
    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout linearLayout;
    Button upAndDown;
    RecyclerView bottomRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonSheet();

        testFirebasePlayAudio();
    }

    private void testFirebasePlayAudio() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp1 == null) {
                    mp1 = new MediaPlayer();
                    mp1.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mp1.setDataSource("https://firebasestorage.googleapis.com/v0/b/meditation-7c5e1.appspot.com/o/testaudio.mp3?alt=media&token=951a7cce-5635-4501-8c22-c1e695e7b926");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp1.prepareAsync();
                    mp1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp1.start();
                        }
                    });
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp1 != null) {
                    mp1.stop();
                    mp1.release();
                    mp1 = null;
                }
            }
        });
    }

    private void setButtonSheet() {
        this.bottomSheetTitleBar = findViewById(R.id.bottom_sheet_title_bar);
        this.upAndDown = findViewById(R.id.bottom_upanddown);
        this.bottomRecyclerView = findViewById(R.id.bottom_recyclerview);
        linearLayout = findViewById(R.id.bottom_sheet_id);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        bottomSheetTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
    }


}