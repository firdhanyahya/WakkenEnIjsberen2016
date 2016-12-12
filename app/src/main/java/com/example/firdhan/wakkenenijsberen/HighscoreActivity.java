package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class HighscoreActivity extends AppCompatActivity {

    FrameLayout frameScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        frameScores = (FrameLayout)findViewById(R.id.frameScores);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(frameScores.getId(), new HighscoresFragment());
        ft.commit();
    }
}
