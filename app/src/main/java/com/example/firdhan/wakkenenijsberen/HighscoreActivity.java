package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class HighscoreActivity extends AppCompatActivity {

    FrameLayout frameScores;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");
        back = (Button)findViewById(R.id.highscore_backbutton);
        back.setTypeface(iceFont);
        frameScores = (FrameLayout)findViewById(R.id.frameScores);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(frameScores.getId(), new HighscoresFragment());
        ft.commit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
