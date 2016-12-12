package com.example.firdhan.wakkenenijsberen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.firdhan.wakkenenijsberen.GameManager.GameLevel1;
import com.example.firdhan.wakkenenijsberen.GameManager.GameLevel2;

public class LevelPicker extends AppCompatActivity {

    Button level1;
    Button level2;
    Button level3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_level_picker);
        //* custom font voor de main screen \\*
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        level1 = (Button)findViewById(R.id.lvl1Btn);
        level2 = (Button)findViewById(R.id.lvl2Btn);
        level3 = (Button)findViewById(R.id.lvl3Btn);

        level1.setTypeface(iceFont);
        level2.setTypeface(iceFont);
        level3.setTypeface(iceFont);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelPicker.this, GameLevel1.class);
                startActivity(i);
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelPicker.this, GameLevel2.class);
                startActivity(i);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelPicker.this, GameLevel1.class);
                startActivity(i);
            }
        });
    }
}
