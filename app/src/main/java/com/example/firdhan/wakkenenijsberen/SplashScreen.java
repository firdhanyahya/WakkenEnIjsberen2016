package com.example.firdhan.wakkenenijsberen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Robin.T on 28-11-2016.
 */

public class SplashScreen extends Activity {
    //Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        new Handler().postDelayed(new Runnable() {
            /*
             * Laat splashscreen zien met timer
             */
            @Override
            public void run() {

                // Deze methode zal uitgevoerd worden wanneer de timer klaar is
                // Start de app main activity

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // sluit de activity
                finish();
            }

        }, SPLASH_TIME_OUT);
    }
}