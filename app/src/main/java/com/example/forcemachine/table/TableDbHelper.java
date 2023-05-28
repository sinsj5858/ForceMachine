package com.example.forcemachine.table;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.forcemachine.menu.MenuDb;
import com.example.forcemachine.menu.MenuTableInfo;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;

public class TableDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TableExist.db";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TableInfo.TABLE_NAME +" ("+
                    TableInfo.COLUMN_NAME_ID+" INTEGER PRIMARY KEY,"+
                    TableInfo.COLUMN_NAME_TABLEID+" TEXT,"+
                    TableInfo.COLUMN_NAME_TABLEEXIST +" BOOLEAN)";


    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public TableDbHelper(@Nullable Context context) {
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
    public ArrayList<TableDb> getTableExistByTableId(String tableId) {
        ArrayList<TableDb> tableList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TableInfo.COLUMN_NAME_ID,
                TableInfo.COLUMN_NAME_TABLEID,
                TableInfo.COLUMN_NAME_TABLEEXIST
        };

        String selection = TableInfo.COLUMN_NAME_TABLEID + " = ?";
        String[] selectionArgs = { tableId };

        Cursor cursor = db.query(
                TableInfo.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_NAME_ID));
            String tableName = cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_NAME_TABLEID));
            boolean tableExist = cursor.getInt(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_NAME_TABLEEXIST)) == 1;
            TableDb table = new TableDb(id, tableName, tableExist);
            tableList.add(table);
        }

        cursor.close();

        return tableList;
    }

    public void deleteTableExistByTableId(String tableId) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = TableInfo.COLUMN_NAME_TABLEID + " = ?";
        String[] selectionArgs = { tableId };

        db.delete(TableInfo.TABLE_NAME, selection, selectionArgs);
    }

    public void updateTableExistByTableId(String tableId, boolean tableExist) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableInfo.COLUMN_NAME_TABLEEXIST, tableExist);

        String selection = TableInfo.COLUMN_NAME_TABLEID + " = ?";
        String[] selectionArgs = { tableId };

        db.update(TableInfo.TABLE_NAME, values, selection, selectionArgs);
    }

}