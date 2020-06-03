package com.strawberry.delivery.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME="food.db";
    private static final int DB_VERSION=1;

    public DBhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE IF NOT EXISTS RestFood ("+
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "foodName TEXT NOT NULL,"+
                "foodPrice INTEGER NOT NULL,"+
                "foodUrl TEXT NOT NULL,"+
                "restaurant INTEGER NOT NULL);"
                ;

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS RestFood";
        db.execSQL(sql);
        onCreate(db);
    }
}
