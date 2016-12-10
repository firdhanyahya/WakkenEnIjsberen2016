package com.example.firdhan.wakkenenijsberen;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Robin on 3-12-2016.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences bestandsnaam
    private static final String PREF_NAME = "welkom";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String PENGUINS = "PenguinsSetting";
    private static final String TIMER = "TimerSetting";
    private static final String LANGUAGE = "LanguageSetting";
    private static final String DICES = "DicesSetting";

    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTimeLaunch){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch);
        editor.commit();
    }

    public  boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public  void  setDefaultSetting(){
        if (isFirstTimeLaunch() == true) {
            editor.putBoolean(PENGUINS, true);
            editor.putBoolean(TIMER, true);
            editor.putString(LANGUAGE, "Nederlands");
            editor.putString(DICES, "3");
            editor.commit();
        }
    }

    public boolean getPenguinsSetting() {
        return pref.getBoolean(PENGUINS, true);
    }

    public boolean getTimerSetting() {
        return pref.getBoolean(TIMER, true);
    }

    public String getLanguageSetting() {
        return pref.getString(LANGUAGE, "Nederlands");
    }

    public String getDicesSetting() {
        return pref.getString(DICES, "3");
    }

    public void editPenguinsSettings(boolean penguins) {
        pref.edit().putBoolean(PENGUINS, penguins).apply();
        editor.commit();
    }

    public void editTimerSettings(boolean timer) {
        pref.edit().putBoolean(TIMER, timer).apply();
        editor.commit();
    }

    public void editLanguageSettings(String language) {
        pref.edit().putString(LANGUAGE, language).apply();
        editor.commit();
    }

    public void editDicesSettings(String dice) {
        pref.edit().putString(DICES, dice).apply();
        editor.commit();
    }
}
