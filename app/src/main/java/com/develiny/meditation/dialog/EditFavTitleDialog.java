package com.develiny.meditation.dialog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.R;
import com.develiny.meditation.databasehandler.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EditFavTitleDialog {

    public static AlertDialog alertDialog;

    private static EditText editText;
    private static Button okbtn, cancel;
    private static DatabaseHandler databaseHandler;

    public static void editFavTitleDialog(Context context, String title, int position) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) vi.inflate(R.layout.edit_fav_title_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();

        alertDialog.show();

        editText = layout.findViewById(R.id.edit_fav_title_dialog_edittext);
        okbtn = layout.findViewById(R.id.edit_fav_title_dialog_button_ok);
        cancel = layout.findViewById(R.id.edit_fav_title_dialog_button_cancel);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() != 0) {
                    databaseHandler = new DatabaseHandler(context);
                    databaseHandler.changeFavTitleName(title, editText.getText().toString());

                } else {
                    Toast.makeText(context, "please edit title name", Toast.LENGTH_SHORT).show();
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
