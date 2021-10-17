package com.develiny.meditation.databasehandler;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.develiny.meditation.page.item.PageItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "list.sqlite";
    public static final String DBLOCATION = "/data/data/com.develiny.meditation/databases/";

    private static final String TABLE_NAME = "list1";

    public static final String COLUMN_INDEX = "position";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_SEEK = "seek";

    private static final String PAGE1_TEAM = "create table if not exists " + TABLE_NAME + "(" + COLUMN_INDEX + " INTEGER," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER" + ");";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void setDB(Context context) {
        File folder = new File(DBLOCATION);
        if (folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = context.getResources().getAssets();
        File outfile = new File(DBLOCATION + DATABASE_NAME);
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } else {
            }
        } catch (IOException e) {}
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PAGE1_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
        sqLiteDatabase.execSQL(PAGE1_TEAM);
    }

    public void openDatabase() {
        String dbPath = DBLOCATION + DATABASE_NAME;
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabse() {
        if(sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<PageItem> page1List() {
        PageItem pageItem = null;
        ArrayList<PageItem> pageItems = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM list1";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pageItem = new PageItem(cursor.getInt(0), cursor.getBlob(1), cursor.getInt(2));
            pageItems.add(pageItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return pageItems;
    }
}
