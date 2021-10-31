package com.develiny.meditation.databasehandler;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.develiny.meditation.MainActivity;
import com.develiny.meditation.audiocontroller.AudioController;
import com.develiny.meditation.dialog.AddTitleDialog;
import com.develiny.meditation.dialog.EditFavTitleDialog;
import com.develiny.meditation.notification.NotificationService;
import com.develiny.meditation.page.FavPage;
import com.develiny.meditation.page.Page1;
import com.develiny.meditation.page.Page2;
import com.develiny.meditation.page.adapter.BottomSheetAdapter;
import com.develiny.meditation.page.adapter.PageAdapter;
import com.develiny.meditation.page.item.FavListItem;
import com.develiny.meditation.page.item.FavTitleItem;
import com.develiny.meditation.page.item.PageItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "list.sqlite";
    public static final String DBLOCATION = "/data/data/com.develiny.meditation/databases/";

    private static final String FAV_TABLE_NAME1 = "fav01";
    private static final String FAV_TABLE_NAME2 = "fav02";
    private static final String FAV_TABLE_NAME3 = "fav03";
    private static final String FAV_TABLE_NAME4 = "fav04";
    private static final String FAV_TABLE_NAME5 = "fav05";
    private static final String FAV_TABLE_NAME6 = "fav06";
    private static final String FAV_TABLE_NAME7 = "fav07";
    private static final String FAV_TABLE_NAME8 = "fav08";
    private static final String FAV_TABLE_NAME9 = "fav09";
    private static final String FAV_TABLE_NAME10 = "fav10";
    private static final String FAV_TABLE_NAME11 = "fav11";
    private static final String FAV_TABLE_NAME12 = "fav12";
    private static final String FAV_TABLE_NAME13 = "fav13";
    private static final String FAV_TABLE_NAME14 = "fav14";
    private static final String FAV_TABLE_NAME15 = "fav15";
    private static final String FAV_TABLE_NAME16 = "fav16";
    private static final String FAV_TABLE_NAME17 = "fav17";
    private static final String FAV_TABLE_NAME18 = "fav18";
    private static final String FAV_TABLE_NAME19 = "fav19";
    private static final String FAV_TABLE_NAME20 = "fav20";
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

    //fav
    private static final String FAV_TITLE_TABLE_NAME = "favtitle";
    public static final String COLUMN_FAV_TITLE = "title";
    public static final String COLUMN_FAV_ISPLAY = "isplay";
    private static final String FAV_LIST_TABLE_NAME = "favlist";
    public static final String COLUMN_FAVTITLENAME = "favtitlename";

    private static final String FAV_TEAM1 = "create table if not exists " + FAV_TABLE_NAME1 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM2 = "create table if not exists " + FAV_TABLE_NAME2 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM3 = "create table if not exists " + FAV_TABLE_NAME3 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM4 = "create table if not exists " + FAV_TABLE_NAME4 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM5 = "create table if not exists " + FAV_TABLE_NAME5 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM6 = "create table if not exists " + FAV_TABLE_NAME6 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM7 = "create table if not exists " + FAV_TABLE_NAME7 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM8 = "create table if not exists " + FAV_TABLE_NAME8 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM9 = "create table if not exists " + FAV_TABLE_NAME9 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM10 = "create table if not exists " + FAV_TABLE_NAME10 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM11 = "create table if not exists " + FAV_TABLE_NAME11 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM12 = "create table if not exists " + FAV_TABLE_NAME12 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM13 = "create table if not exists " + FAV_TABLE_NAME13 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM14 = "create table if not exists " + FAV_TABLE_NAME14 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM15 = "create table if not exists " + FAV_TABLE_NAME15 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM16 = "create table if not exists " + FAV_TABLE_NAME16 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM17 = "create table if not exists " + FAV_TABLE_NAME17 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM18 = "create table if not exists " + FAV_TABLE_NAME18 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM19 = "create table if not exists " + FAV_TABLE_NAME19 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String FAV_TEAM20 = "create table if not exists " + FAV_TABLE_NAME20 + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String PLAYING_TEAM = "create table if not exists " + PLAYING_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String RAIN_TEAM = "create table if not exists " + RAIN_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";
    private static final String WIND_TEAM = "create table if not exists " + WIND_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER" + ");";

    private static final String FAV_TITLE_TEAM = "create table if not exists " + FAV_TITLE_TABLE_NAME + "(" + COLUMN_FAV_TITLE + " TEXT," + COLUMN_FAV_ISPLAY + " INTEGER" + ");";
    private static final String FAV_LIST_TEAM = "create table if not exists " + WIND_TABLE_NAME + "(" + COLUMN_PAGE + " INTEGER," + COLUMN_POSITION + " INTEGER," + COLUMN_PNP + " TEXT, " + COLUMN_IMGDEFAULT + " BLOB," + COLUMN_IMAGE + " BLOB," + COLUMN_SEEK + " INTEGER," + COLUMN_ISPLAY + " INTEGER," +  COLUMN_FAVTITLENAME + " INTEGER" + ");";

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
        sqLiteDatabase.execSQL(FAV_TEAM1);
        sqLiteDatabase.execSQL(FAV_TEAM2);
        sqLiteDatabase.execSQL(FAV_TEAM3);
        sqLiteDatabase.execSQL(FAV_TEAM4);
        sqLiteDatabase.execSQL(FAV_TEAM5);
        sqLiteDatabase.execSQL(FAV_TEAM6);
        sqLiteDatabase.execSQL(FAV_TEAM7);
        sqLiteDatabase.execSQL(FAV_TEAM8);
        sqLiteDatabase.execSQL(FAV_TEAM9);
        sqLiteDatabase.execSQL(FAV_TEAM10);
        sqLiteDatabase.execSQL(FAV_TEAM11);
        sqLiteDatabase.execSQL(FAV_TEAM12);
        sqLiteDatabase.execSQL(FAV_TEAM13);
        sqLiteDatabase.execSQL(FAV_TEAM14);
        sqLiteDatabase.execSQL(FAV_TEAM15);
        sqLiteDatabase.execSQL(FAV_TEAM16);
        sqLiteDatabase.execSQL(FAV_TEAM17);
        sqLiteDatabase.execSQL(FAV_TEAM18);
        sqLiteDatabase.execSQL(FAV_TEAM19);
        sqLiteDatabase.execSQL(FAV_TEAM20);
        sqLiteDatabase.execSQL(PLAYING_TEAM);
        sqLiteDatabase.execSQL(RAIN_TEAM);
        sqLiteDatabase.execSQL(WIND_TEAM);
        sqLiteDatabase.execSQL(FAV_TITLE_TEAM);
        sqLiteDatabase.execSQL(FAV_LIST_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME3);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME4);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME5);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME6);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME7);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME8);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME9);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME10);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME11);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME12);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME13);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME14);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME15);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME16);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME17);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME18);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME19);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TABLE_NAME20);
        sqLiteDatabase.execSQL("DROP TABLE " + PLAYING_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + RAIN_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + WIND_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_TITLE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + FAV_LIST_TABLE_NAME);
        sqLiteDatabase.execSQL(FAV_TEAM1);
        sqLiteDatabase.execSQL(FAV_TEAM2);
        sqLiteDatabase.execSQL(FAV_TEAM3);
        sqLiteDatabase.execSQL(FAV_TEAM4);
        sqLiteDatabase.execSQL(FAV_TEAM5);
        sqLiteDatabase.execSQL(FAV_TEAM6);
        sqLiteDatabase.execSQL(FAV_TEAM7);
        sqLiteDatabase.execSQL(FAV_TEAM8);
        sqLiteDatabase.execSQL(FAV_TEAM9);
        sqLiteDatabase.execSQL(FAV_TEAM10);
        sqLiteDatabase.execSQL(FAV_TEAM11);
        sqLiteDatabase.execSQL(FAV_TEAM12);
        sqLiteDatabase.execSQL(FAV_TEAM13);
        sqLiteDatabase.execSQL(FAV_TEAM14);
        sqLiteDatabase.execSQL(FAV_TEAM15);
        sqLiteDatabase.execSQL(FAV_TEAM16);
        sqLiteDatabase.execSQL(FAV_TEAM17);
        sqLiteDatabase.execSQL(FAV_TEAM18);
        sqLiteDatabase.execSQL(FAV_TEAM19);
        sqLiteDatabase.execSQL(FAV_TEAM20);
        sqLiteDatabase.execSQL(PLAYING_TEAM);
        sqLiteDatabase.execSQL(RAIN_TEAM);
        sqLiteDatabase.execSQL(WIND_TEAM);
        sqLiteDatabase.execSQL(FAV_TITLE_TEAM);
        sqLiteDatabase.execSQL(FAV_LIST_TEAM);
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

    public ArrayList<FavTitleItem> favTitleList() {
        FavTitleItem favTitleItem = null;
        ArrayList<FavTitleItem> favTitleItems = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM favtitle";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            favTitleItem = new FavTitleItem(cursor.getString(0), cursor.getInt(1));
            if (favTitleItem.getTitle() != null) {
                favTitleItems.add(favTitleItem);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return favTitleItems;
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

    public ArrayList<FavListItem> getFavListItem(String title) {
        FavListItem favListItem = null;
        ArrayList<FavListItem> favListItems = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from favlist where favtitlename = " + "'" + title + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            favListItem = new FavListItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getBlob(3), cursor.getBlob(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7));
            favListItems.add(favListItem);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        Log.d(">>>favlist", "size: " + favListItems.size());
        return favListItems;
    }

    public void deleteAllPlayingListTest() {
        sqLiteDatabase = this.getWritableDatabase();
//        sqLiteDatabase.execSQL("delete from playing");
//        sqLiteDatabase.execSQL("update rain set isplay = 1");
//        sqLiteDatabase.execSQL("update wind set isplay = 1");
//        sqLiteDatabase.execSQL("update favtitle set title = null");
        sqLiteDatabase.execSQL("delete from fav01");
        sqLiteDatabase.execSQL("delete from fav02");
        sqLiteDatabase.execSQL("delete from fav03");
        sqLiteDatabase.execSQL("delete from fav04");
        sqLiteDatabase.execSQL("delete from fav05");
        sqLiteDatabase.execSQL("delete from fav06");
        sqLiteDatabase.execSQL("delete from fav07");
        sqLiteDatabase.execSQL("delete from fav08");
        sqLiteDatabase.execSQL("delete from fav09");
        sqLiteDatabase.execSQL("delete from fav10");
        sqLiteDatabase.execSQL("delete from fav11");
        sqLiteDatabase.execSQL("delete from fav12");
        sqLiteDatabase.execSQL("delete from fav13");
        sqLiteDatabase.execSQL("delete from fav14");
        sqLiteDatabase.execSQL("delete from fav15");
        sqLiteDatabase.execSQL("delete from fav16");
        sqLiteDatabase.execSQL("delete from fav17");
        sqLiteDatabase.execSQL("delete from fav18");
        sqLiteDatabase.execSQL("delete from fav19");
        sqLiteDatabase.execSQL("delete from fav20");
        sqLiteDatabase.execSQL("delete from favlist");
        sqLiteDatabase.execSQL("delete from favtitle");
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

    public void checkTitleAlready(Context context, String title) {
        List<String> titles = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select title from favtitle", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            titles.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        if (titles.contains(title)) {
            Toast.makeText(context, "같은이름", Toast.LENGTH_SHORT).show();
        } else {
            checkSameFavList(context, title);
//            addFavTitleList(title);
        }
    }

    public void checkSameFavList(Context context, String title) {
        Log.d(">>>check", "4");
        List<String> nowPnps = new ArrayList<>();//현제 playinglist의 pnp list
        for (int i = 0; i < MainActivity.playingList.size(); i++) {
            nowPnps.add(MainActivity.playingList.get(i).getPnp());
            if (i == MainActivity.playingList.size() - 1) {
                sqLiteDatabase = this.getWritableDatabase();
                List<String> favtitles = new ArrayList<>();//현제 favtitle들의 title list
                Cursor cursor = sqLiteDatabase.rawQuery("select title from favtitle", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    favtitles.add(cursor.getString(0));
                    cursor.moveToNext();
                }


                if (favtitles.size() != 0) {//여기서 error
                    if (haveSame(nowPnps, favtitles)) {
                        Toast.makeText(context, "same already", Toast.LENGTH_SHORT).show();
                    } else {
                        addFavTitleList(title);
                    }
                } else {
                    addFavTitleList(title);
                }
            }
        }
    }

    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {//순서 무시 똑같으면 true
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public void addFavTitleList(String title1) {
        FavTitleItem favTitleItem = null;
        sqLiteDatabase = this.getWritableDatabase();

        if (MainActivity.playingList.size() != 0) {
            String pnp = MainActivity.playingList.get(0).getPnp();
            if (AudioController.playingListindex0_1(pnp).isPlaying() || AudioController.playingListindex0_2(pnp).isPlaying()) {
                sqLiteDatabase.execSQL("insert into favtitle values (" + "'" + title1 + "'" + "," + 2 + ")");
                favTitleItem = new FavTitleItem(title1, 2);
            } else {
                sqLiteDatabase.execSQL("insert into favtitle values (" + "'" + title1 + "'" + "," + 1 + ")");
                favTitleItem = new FavTitleItem(title1, 1);
            }
            FavPage.favTitleItemArrayList.add(favTitleItem);
            FavPage.adapter.notifyItemInserted(FavPage.favTitleItemArrayList.size() - 1);
            FavPage.adapter.notifyDataSetChanged();
            if (AddTitleDialog.alertDialog.isShowing()) {
                AddTitleDialog.alertDialog.dismiss();
            }
            addFavList(title1);
        }
    }

    public void addFavList(String title) {
        sqLiteDatabase = this.getWritableDatabase();

        for (int i = 0; i < MainActivity.playingList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("page", MainActivity.playingList.get(i).getPage());
            contentValues.put("position", MainActivity.playingList.get(i).getPosition());
            contentValues.put("pnp", MainActivity.playingList.get(i).getPnp());
            contentValues.put("imagedefault", MainActivity.playingList.get(i).getImgdefault());
            contentValues.put("img", MainActivity.playingList.get(i).getImg());
            contentValues.put("seek", MainActivity.playingList.get(i).getSeek());
            contentValues.put("isplay", MainActivity.playingList.get(i).getIsplay());
            contentValues.put("favtitlename", title);
            sqLiteDatabase.insert("favlist", null, contentValues);
        }
    }

    public void removeFavList(String title) {
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select _rowid_ from favtitle where title = " + title, null);
        cursor.moveToFirst();
        int rowid = cursor.getInt(0);
        sqLiteDatabase.execSQL("delete from favtitle where _rowid_ = " + rowid);
        sqLiteDatabase.execSQL("delete from favlist where favtitlename = " + rowid);
        FavPage.favTitleItemArrayList.remove(rowid - 1);
        FavPage.adapter.notifyItemRemoved(rowid - 1);
        FavPage.adapter.notifyDataSetChanged();
        sqLiteDatabase.execSQL("vacuum");
        cursor.close();
        closeDatabse();
    }

    String getNextFav(int index) {
        if (index == 1) {
            return "fav01";
        } else if (index == 2) {
            return "fav02";
        } else if (index == 3) {
            return "fav03";
        } else if (index == 4) {
            return "fav04";
        } else if (index == 5) {
            return "fav05";
        } else if (index == 6) {
            return "fav06";
        } else if (index == 7) {
            return "fav07";
        } else if (index == 8) {
            return "fav08";
        } else if (index == 9) {
            return "fav09";
        } else if (index == 10) {
            return "fav10";
        } else if (index == 11) {
            return "fav11";
        } else if (index == 12) {
            return "fav12";
        } else if (index == 13) {
            return "fav13";
        } else if (index == 14) {
            return "fav14";
        } else if (index == 15) {
            return "fav15";
        } else if (index == 16) {
            return "fav16";
        } else if (index == 17) {
            return "fav17";
        } else if (index == 18) {
            return "fav18";
        } else if (index == 19) {
            return "fav19";
        } else if (index == 20) {
            return "fav20";
        } else {
            return null;
        }
    }

    boolean haveSame(List<String> nowPnps, List<String> favtitles) {
        boolean isSame = false;
        sqLiteDatabase = this.getWritableDatabase();
        List<String> checkWithThisList = new ArrayList<>();
        for (int i = 0; i < favtitles.size(); i++) {
            Cursor cursor = sqLiteDatabase.rawQuery("select pnp from favlist where favtitlename = " + "'" + favtitles.get(i) + "'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                checkWithThisList.add(cursor.getString(0));
                cursor.moveToNext();
                //move to next 없어서 error난걸수도있음
            }
            if (listEqualsIgnoreOrder(checkWithThisList, nowPnps)) {
                isSame = true;
                break;
            } else {
                isSame = false;
            }
        }
        if (isSame) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteAllPlayinglist(ArrayList<Integer> pagelist, ArrayList<Integer> positionlist, String title) {
        Log.d(">>>DatabaseHandler", "check, title: + " + title);
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from playing");
        sqLiteDatabase.execSQL("update rain set isplay = 1 where isplay = 2");
        sqLiteDatabase.execSQL("update wind set isplay = 1 where isplay = 2");
        for (int i = 0; i < pagelist.size(); i++) {
            if (pagelist.get(i) == 1) {
                sqLiteDatabase.execSQL("update rain set isplay = 2 where position = " + positionlist.get(i));
                Page1.arrayList.get(positionlist.get(i) - 1).setIsplay(1);
                Page1.adapter.notifyItemChanged(positionlist.get(i) - 1);
                Page1.adapter.notifyDataSetChanged();
            } else if (pagelist.get(i) == 2) {
                sqLiteDatabase.execSQL("update wind set isplay = 2 where position = " + positionlist.get(i));
                Page2.arrayList.get(positionlist.get(i) - 1).setIsplay(1);
                Page2.adapter.notifyItemChanged(positionlist.get(i) - 1);
                Page2.adapter.notifyDataSetChanged();
            }
        }
        sqLiteDatabase.execSQL("vacuum");

        addFavListInPlayinglist(title);
    }

    public void addFavListInPlayinglist(String title) {
        Log.d(">>>DatabaseHandler", "check, title: " + title);
        sqLiteDatabase = this.getWritableDatabase();
        PageItem pageItem = null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from favlist where favtitlename = " + "'" + title + "'", null);
        cursor.moveToFirst();
        int count = 0;
        while (!cursor.isAfterLast()) {
            count += 1;
            pageItem = new PageItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getBlob(3), cursor.getBlob(4), cursor.getInt(5), 2);
            MainActivity.playingList.add(pageItem);
            ContentValues contentValues = new ContentValues();
            contentValues.put("page", cursor.getInt(0));
            contentValues.put("position", cursor.getInt(1));
            contentValues.put("pnp", cursor.getString(2));
            contentValues.put("imgdefault", cursor.getBlob(3));
            contentValues.put("img", cursor.getBlob(4));
            contentValues.put("seek", cursor.getInt(5));
            contentValues.put("isplay", 2);
            sqLiteDatabase.insert("playing", null, contentValues);//2개 이상이여도 하나만 추가됨(error)//1-1만 추가됨(없어도)
            cursor.moveToNext();
        }
        MainActivity.bottomSheetAdapter.notifyItemRangeInserted(0, count);
        MainActivity.bottomSheetAdapter.notifyDataSetChanged();
        cursor.close();
        closeDatabse();
    }

    public void changeFavTitleName(String oldTitle, String newTitle) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("update favtitle set title = " + "'" + newTitle + "' where title = " + "'" + oldTitle + "'");
        sqLiteDatabase.execSQL("update favlist set favtitlename = " + "'" + newTitle + "' where favtitlename = " + "'" + oldTitle + "'");
        for (int i = 0; i < FavPage.favTitleItemArrayList.size(); i++) {
            if (FavPage.favTitleItemArrayList.get(i).getTitle().equals(oldTitle)) {
                FavPage.favTitleItemArrayList.get(i).setTitle(newTitle);
                FavPage.adapter.notifyItemChanged(i);
                FavPage.adapter.notifyDataSetChanged();
                if (EditFavTitleDialog.alertDialog.isShowing()) {
                    EditFavTitleDialog.alertDialog.dismiss();
                }
                break;
            }
        }
    }

    List<String> favPnps(int size, String favtitle) {
        String pnp;
        List<String> pnps = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        for (int i = 0; i < size; i++) {
            Cursor cursor = sqLiteDatabase.rawQuery("select pnp from favlist where title = " + favtitle, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pnp = cursor.getString(0);
                pnps.add(pnp);
            }
        }
        return pnps;
    }

    String getPageName(int page) {
        if (page == 1) {
            return "rain";
        } else if (page == 2) {
            return "wind";
        } else {
            return "nul";
        }
    }

    public ArrayList<String> fav01to20(int i) {
        String favNum;
        if (i < 10) {
            favNum = "fav0" + Integer.toString(i);
        } else {
            favNum = "fav" + Integer.toString(i);
        }

        String pnpInFav;
        ArrayList<String> pnpInFavs = new ArrayList<>();

        openDatabase();
        String sql = "SELECT pnp FROM " + favNum;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pnpInFav = new String(cursor.getString(0));
            pnpInFavs.add(pnpInFav);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabse();
        return pnpInFavs;
    }
}
