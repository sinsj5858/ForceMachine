package com.example.forcemachine.menu;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MenuDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE "+ MenuTableInfo.TABLE_NAME +" ("+
                    MenuTableInfo.COLUMN_NAME_ID+" INTEGER PRIMARY KEY,"+
                    MenuTableInfo.COLUMN_NAME_TABLEID+" TEXT,"+
                    MenuTableInfo.COLUMN_NAME_MENUNAME+" TEXT,"+
                    MenuTableInfo.COLUMN_NAME_MENUPRICE+" TEXT,"+
                    MenuTableInfo.COLUMN_NAME_MENUCOUNT+" INTEGER)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + MenuTableInfo.TABLE_NAME;

    public MenuDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // 테이블 데이터 가져오기  (tableId를 활용하여)
    public ArrayList<MenuDb> getMenuByTableId(String tableId) {
        ArrayList<MenuDb> menuList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                MenuTableInfo.COLUMN_NAME_ID,
                MenuTableInfo.COLUMN_NAME_TABLEID,
                MenuTableInfo.COLUMN_NAME_MENUNAME,
                MenuTableInfo.COLUMN_NAME_MENUPRICE,
                MenuTableInfo.COLUMN_NAME_MENUCOUNT
        };

        String selection = MenuTableInfo.COLUMN_NAME_TABLEID + " = ?";
        String[] selectionArgs = { tableId };

        Cursor cursor = db.query(
                MenuTableInfo.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MenuTableInfo.COLUMN_NAME_ID));
            String tableName = cursor.getString(cursor.getColumnIndexOrThrow(MenuTableInfo.COLUMN_NAME_TABLEID));
            String menuName = cursor.getString(cursor.getColumnIndexOrThrow(MenuTableInfo.COLUMN_NAME_MENUNAME));
            String menuPrice = cursor.getString(cursor.getColumnIndexOrThrow(MenuTableInfo.COLUMN_NAME_MENUPRICE));
            int menuCount = cursor.getInt(cursor.getColumnIndexOrThrow(MenuTableInfo.COLUMN_NAME_MENUCOUNT));

            MenuDb menu = new MenuDb(id, tableName, menuName, menuPrice, menuCount);
            menuList.add(menu);
        }

        cursor.close();

        return menuList;
    }

    public void deleteMenuByTableId(String tableId) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = MenuTableInfo.COLUMN_NAME_TABLEID + " = ?";
        String[] selectionArgs = { tableId };

        db.delete(MenuTableInfo.TABLE_NAME, selection, selectionArgs);
    }

}