package com.develiny.meditation.databasehandler;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;
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

    private static final String FAV_TABLE_NAME = "fav";
    private static final String PLAYING_TABLE_NAME = "playing";
    private static final String RAIN_TABLE_NAME = "rain";
    private static final String WIND_TABLE_NAME = "wind";

    public static final String COLUMN_PAGE = "page";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_PNP = "pnp";
    public static final String COLUMN_IMGDEFAULT = "imgdefault";
    public static final String COLUMN_IMAGE = "img";
    public static final String COLUMN_SEEK = "seek";
    public static final String COLUMN_ISPLAY = "isplay";

    private static final String FAV_TEAM = "create table if not exists " + FAV_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String PLAYING_TEAM = "create table if not exists " + FAV_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String RAIN_TEAM = "create table if not exists " + FAV_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String WIND_TEAM = "create table if not exists " + FAV_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";

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
        } catch (IOException e) {
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FAV_TEAM);
        sqLiteDatabase.execSQL(PLAYING_TEAM);
        sqLiteDatabase.execSQL(RAIN_TEAM);
        sqLiteDatabase.execSQL(WIND_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + PLAYING_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + RAIN_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + WIND_TABLE_NAME);
        sqLiteDatabase.execSQL(RAIN_TEAM);
    }

    public void openDatabase() {
        String dbPath = DBLOCATION + DATABASE_NAME;
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabse() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<PageItem> playingList() {
        PageItem pageItem = null;
        ArrayList<PageItem> pageItems = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM playing";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pageItem = new PageItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getBlob(3), cursor.getBlob(4), cursor.getInt(5), cursor.getInt(6));
            pageItems.add(pageItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return pageItems;
    }

    public ArrayList<PageItem> rainList() {
        PageItem pageItem = null;
        ArrayList<PageItem> pageItems = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM rain";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pageItem = new PageItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getBlob(3), cursor.getBlob(4), cursor.getInt(5), cursor.getInt(6));
            pageItems.add(pageItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return pageItems;
    }

    public ArrayList<PageItem> windList() {
        PageItem pageItem = null;
        ArrayList<PageItem> pageItems = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM wind";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pageItem = new PageItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getBlob(3), cursor.getBlob(4), cursor.getInt(5), cursor.getInt(6));
            pageItems.add(pageItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return pageItems;
    }

    public void deleteAllPlayingList() {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from playing");
        sqLiteDatabase.execSQL("update rain set isplay = 1");
        sqLiteDatabase.execSQL("update wind set isplay = 1");
    }

    public void deletePlayingList(int page, int position) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from playing where page = " + page);
        sqLiteDatabase.execSQL("update " + getPageName(page) + " set isplay = 1 where position = " + position);
    }

    public void setPlay1(int page, int position) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update " + getPageName(page) + " set isplay = 1");
        sqLiteDatabase.execSQL("delete from playing where page = " + page);
        sqLiteDatabase.execSQL("update " + getPageName(page) + " set isplay = 2 where position = " + position);
        sqLiteDatabase.execSQL("insert into playing select * from " + getPageName(page) + " where position = " + position);
    }

    public void updateVolumn(int page, int position) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update " + getPageName(page) + " set seek where posision = " + position);
    }

    String getPageName(int page) {
        if(page == 1) {
            return "rain";
        } else if (page == 2) {
            return "wind";
        } else {
            return "nul";
        }
    }
}
