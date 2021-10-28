package com.develiny.meditation.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;

public class AddTitleDialog {

    public static AlertDialog alertDialog;

    private static EditText editText;
    private static Button okbtn, cancel;
    private static DatabaseHandler databaseHandler;

    public static void addTitleDialog(Context context) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) vi.inflate(R.layout.add_title_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();

        alertDialog.show();

        editText = layout.findViewById(R.id.add_title_edittext);
        okbtn = layout.findViewById(R.id.add_title_button_ok);
        cancel = layout.findViewById(R.id.add_title_button_cancel);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.playingList.size() != 0) {
                    databaseHandler = new DatabaseHandler(context);
                    for (int i = 0; i < MainActivity.playingList.size(); i++) {
                        if (editText.getText().toString().length() != 0) {
                            databaseHandler.checkTitleAlready(alertDialog.getContext(), editText.getText().toString());
                        }
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
