package com.example.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOperator extends SQLiteOpenHelper {

    private static String CREATE_BOOKMARK =
            "create table Bookmark( " +
            "ID integer primary key autoincrement," +
            "CATEGARY text," +
            "URL text," +
            "TIME text," +
            "FLAG integer" + ")";

    public DBOperator(Context context) {
        super(context, "browserdatabase.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOKMARK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
