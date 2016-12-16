package com.example.firdhan.wakkenenijsberen.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by James on 02/12/2016.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WakkenEnIjsberen";
    private static final String TABLE_NAME = "HighscoresTable";
    private static final String COL_1 = "PlayerID";
    private static final String COL_2 = "PlayerName";
    private static final String COL_3 = "Time";
    private static final String COL_4 = "Level";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "( " + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + " TEXT NOT NULL,"
                + COL_3 + " TEXT NOT NULL,"
                + COL_4 + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void DeleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
    }

    public boolean insertData(String playerName, int timeInSeconds, String level){
        //Sla de tijd op als string met format mm:ss
        int seconds = timeInSeconds % 60;
        int minutes = (timeInSeconds % 3600) / 60;
        String endTime = String.format("%d:%02d", minutes, seconds);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, playerName);
        contentValues.put(COL_3, endTime);
        contentValues.put(COL_4, level);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //Kijk of het toevoegen gelukt is, anders returneer false
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> nameHighscore(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from HighscoresTable WHERE Level = \"" + level + "\" ORDER BY Time", null);
        ArrayList<String> names = new ArrayList<>();

        if (res.getCount() == 0) {
//            Toast.makeText(this, "nothing found", Toast.LENGTH_SHORT).show();
        } else {
            while (res.moveToNext()) {
                names.add(res.getString(1));
            }
        }
        return names;
    }

    public ArrayList<String> timeHighscore(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from HighscoresTable WHERE Level = \"" + level + "\" ORDER BY Time", null);
        ArrayList<String> times = new ArrayList<>();

        if (res.getCount() == 0) {
//            Toast.makeText(this, "nothing found", Toast.LENGTH_SHORT).show();
        } else {
            while (res.moveToNext()) {
                times.add(res.getString(2));
            }
        }
        return times;
    }
}
