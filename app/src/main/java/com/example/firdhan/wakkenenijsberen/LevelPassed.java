package com.example.firdhan.wakkenenijsberen;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LevelPassed extends AppCompatActivity {

    Button terug;
    String name, timeInSecs;
    TextView txtNamePlayer, scoreTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_level_passed);

        terug = (Button)findViewById(R.id.btnBack);
        txtNamePlayer = (TextView)findViewById(R.id.txtName);
        scoreTimer = (TextView)findViewById(R.id.txtScore);

        terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(LevelPassed.this, MainActivity.class);
                startActivity(i);
            }
        });

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                name = extras.getString("name");
                timeInSecs = extras.getString("timer");
            }
            txtNamePlayer.setText("Playername: " + name);
            scoreTimer.setText("Tijd: " + timeInSecs);
        }
    }
}
