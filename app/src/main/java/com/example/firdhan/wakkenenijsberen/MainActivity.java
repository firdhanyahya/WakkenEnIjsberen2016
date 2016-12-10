package com.example.firdhan.wakkenenijsberen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button levels;
    Button howto;
    Button settings; // nog assignen
    Button highscore; // nog assignen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.scrollingText);
        tv.setSelected(true);

        //* custom font voor de main screen \\*
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        levels = (Button)findViewById(R.id.playBtn);
        howto = (Button)findViewById(R.id.howToBtn);
        settings = (Button)findViewById(R.id.settingsBtn);
        highscore = (Button)findViewById(R.id.highscoresBtn);

        tv.setTypeface(iceFont);
        howto.setTypeface(iceFont);
        levels.setTypeface(iceFont);
        settings.setTypeface(iceFont);
        highscore.setTypeface(iceFont);


        howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HowToPlay.class);
                startActivity(i);
            }
        });

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LevelPicker.class);
                startActivity(i);
            }
        });
    }
}
