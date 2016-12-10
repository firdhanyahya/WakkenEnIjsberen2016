package com.example.firdhan.wakkenenijsberen;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by Robin.T on 28-11-2016.
 */

public class SplashScreen extends Activity {
    //Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.splash_screen_layout);

        new Handler().postDelayed(new Runnable() {
            /*
             * Laat splashscreen zien met timer
             */
            @Override
            public void run() {

                // Deze methode zal uitgevoerd worden wanneer de timer klaar is
                // Start de app main activity

                Intent i = new Intent(SplashScreen.this, WelkomActivity.class);
                startActivity(i);

                // sluit de activity
                finish();
            }

        }, SPLASH_TIME_OUT);
    }
}