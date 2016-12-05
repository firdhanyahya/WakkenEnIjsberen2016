package com.example.firdhan.wakkenenijsberen.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

/**
 * Created by James on 02/12/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WakkenEnIjsberen";
    private static final String TABLE_NAME = "WeIHighscores";
    private static final String COL_1 = "PlayerID";
    private static final String COL_2 = "PlayerName";
    private static final String COL_3 = "Time";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " ( " + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " TEXT NOT NULL,"
                + COL_3 + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String playerName, int timeInSeconds){
        //Sla de tijd op als string met format mm:ss
        String endTime = String.format("%d:%02d", timeInSeconds / 60, timeInSeconds % 60);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, playerName);
        contentValues.put(COL_3, endTime);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //Kijk of het toevoegen gelukt is, anders returneer false
        if(result == -1){
            return false;
        } else {
            return true;
        }

    }
}
