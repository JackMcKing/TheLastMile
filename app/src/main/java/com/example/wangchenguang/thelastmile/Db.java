package com.example.wangchenguang.thelastmile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

/**
 * Created by wangchenguang on 15/12/2.
 */
public class Db extends SQLiteOpenHelper {
    public Db(Context context) {
        super(context, "db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "commodity TEXT DEFAULT \"\"," +
                "price TEXT DEFAULT \"\")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
