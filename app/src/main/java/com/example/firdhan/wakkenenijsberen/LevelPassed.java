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

public class LevelPassed extends AppCompatActivity {

    private Button terug;
    private String name, timeInSecs;
    private TextView txtNamePlayer, scoreTimer, congrats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_level_passed);

        //* custom font voor de certificaat screen \\*
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");
        terug = (Button)findViewById(R.id.btnBack);
        txtNamePlayer = (TextView)findViewById(R.id.txtName);
        scoreTimer = (TextView)findViewById(R.id.txtScore);
        congrats = (TextView) findViewById(R.id.levelPassedCongrats);

        terug.setTypeface(iceFont);
        txtNamePlayer.setTypeface(iceFont);
        scoreTimer.setTypeface(iceFont);
        congrats.setTypeface(iceFont);

        terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                name = extras.getString("name");
                timeInSecs = extras.getString("timer");
            }
            txtNamePlayer.setText(getString(R.string.playerNaam) + " " + name);
            scoreTimer.setText(getString(R.string.timeplayed) + " " + timeInSecs);
        }
    }
}
