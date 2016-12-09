package com.example.firdhan.wakkenenijsberen;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button levels;
    Button howto;
    Button settings; // nog assignen
    Button highscore; // nog assignen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //* custom font voor de main screen \\*
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        levels = (Button)findViewById(R.id.playBtn);
        howto = (Button)findViewById(R.id.howToBtn);
        settings = (Button)findViewById(R.id.settingsBtn);
        highscore = (Button)findViewById(R.id.highscoresBtn);

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
