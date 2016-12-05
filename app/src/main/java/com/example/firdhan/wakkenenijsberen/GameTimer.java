package com.example.firdhan.wakkenenijsberen;

import android.os.Handler;
import android.os.SystemClock;

import java.util.TimerTask;

/**
 * Created by James on 25/11/2016.
 */

public class GameTimer extends TimerTask {
    private Handler handler = new Handler();

    @Override
    public void run() {

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 10000);
        }
    };
}
