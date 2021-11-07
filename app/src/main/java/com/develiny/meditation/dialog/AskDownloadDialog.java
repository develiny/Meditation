package com.develiny.meditation.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;
import com.develiny.meditation.page.ChakraPage;
import com.develiny.meditation.page.HzPage;
import com.develiny.meditation.page.adapter.FavTitleAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AskDownloadDialog {
    public static AlertDialog alertDialog;
    private static Button okbtn, cancel;
    private static ProgressBar progressBar;
    private static TextView count;

    private static FirebaseStorage storage;
    private static StorageReference reference;

    public static void askDownloadDialog(Context context, ArrayList<String> pnps, int position) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) vi.inflate(R.layout.ask_download_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();

        alertDialog.show();

        okbtn = layout.findViewById(R.id.ask_download_dialog_button_ok);
        cancel = layout.findViewById(R.id.ask_download_dialog_button_cancel);
        progressBar = layout.findViewById(R.id.ask_download_dialog_progressbar);
        count = layout.findViewById(R.id.ask_download_dialog_count);
        progressBar.setVisibility(View.INVISIBLE);
        count.setVisibility(View.INVISIBLE);

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

        final int[] counter = {0};

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
                progressBar.setMax(pnps.size());
                count.setText("0 / " + pnps.size());
//                int counter = 0;
                progressBar.setProgress(counter[0]);
                for (int i = 0; i < pnps.size(); i++) {
                    String fileName = "audio" + pnps.get(i) + ".mp3";
                    try {
                        File localFile = File.createTempFile("audio", "0");
                        reference.child(fileName).getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                ChakraPage.adapter.notifyDataSetChanged();
                                HzPage.adapter.notifyDataSetChanged();
                                File from = new File(context.getApplicationInfo().dataDir + "/cache", localFile.getName());
                                File to = new File(context.getApplicationInfo().dataDir + "/cache", fileName);
                                if (from.exists()) {
                                    from.renameTo(to);
                                }
                                counter[0] += 1;
                                count.setText(counter[0] + " / " + pnps.size());
                                progressBar.setProgress(counter[0]);
                                if (counter[0] == pnps.size()) {
                                    ChakraPage.setAudio(context);
                                    HzPage.setAudio(context);
                                    alertDialog.dismiss();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}
