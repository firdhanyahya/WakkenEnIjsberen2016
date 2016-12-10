package com.example.firdhan.wakkenenijsberen;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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

        level1 = (Button)findViewById(R.id.lvl1Btn);
        level2 = (Button)findViewById(R.id.lvl2Btn);
        level3 = (Button)findViewById(R.id.lvl3Btn);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelPicker.this, GameLevel1.class);
                startActivity(i);
            }
        });
    }
}
