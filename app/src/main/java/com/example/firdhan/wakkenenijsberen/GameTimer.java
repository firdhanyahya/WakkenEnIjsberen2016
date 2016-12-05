package com.example.firdhan.wakkenenijsberen;

import android.os.Handler;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by James on 25/11/2016.
 */

public class GameTimer extends TimerTask {
    @Override
    public void run() {
        long millis = System.currentTimeMillis(); // - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds     = seconds % 60;
    }
}
