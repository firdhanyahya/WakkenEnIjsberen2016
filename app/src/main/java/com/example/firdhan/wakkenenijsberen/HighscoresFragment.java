package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.firdhan.wakkenenijsberen.Databases.DBHandler;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by Firdhan on 12/12/2016.
 */

public class HighscoresFragment extends Fragment {

    TextView name1, name2, name3, name4, name5, name6, name7, name8, name9, name10;
    TextView time1, time2, time3, time4, time5, time6, time7, time8, time9, time10;
    TextView[] namesView = new TextView[] {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
    TextView[] timesView = new TextView[] {time1, time2, time3, time4, time5, time6, time7, time8, time9, time10};
    Button level1, level2, level3;
    FrameLayout frameScores;
    ArrayList<String> names;
    ArrayList<String> times;
    DBHandler db;

    public HighscoresFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highscores, container, false);

        db = new DBHandler(getActivity());

        name1 = (TextView)v.findViewById(R.id.name1Txt);
        name2 = (TextView)v.findViewById(R.id.name2Txt);
        name3 = (TextView)v.findViewById(R.id.name3Txt);
        name4 = (TextView)v.findViewById(R.id.name4Txt);
        name5 = (TextView)v.findViewById(R.id.name5Txt);
        name6 = (TextView)v.findViewById(R.id.name6Txt);
        name7 = (TextView)v.findViewById(R.id.name7Txt);
        name8 = (TextView)v.findViewById(R.id.name8Txt);
        name9 = (TextView)v.findViewById(R.id.name9Txt);
        name10 = (TextView)v.findViewById(R.id.name10Txt);

        time1 = (TextView)v.findViewById(R.id.time1Txt);
        time2 = (TextView)v.findViewById(R.id.time2Txt);
        time3 = (TextView)v.findViewById(R.id.time3Txt);
        time4 = (TextView)v.findViewById(R.id.time4Txt);
        time5 = (TextView)v.findViewById(R.id.time5Txt);
        time6 = (TextView)v.findViewById(R.id.time6Txt);
        time7 = (TextView)v.findViewById(R.id.time7Txt);
        time8 = (TextView)v.findViewById(R.id.time8Txt);
        time9 = (TextView)v.findViewById(R.id.time9Txt);
        time10 = (TextView)v.findViewById(R.id.time10Txt);

        namesView = new TextView[] {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
        timesView = new TextView[] {time1, time2, time3, time4, time5, time6, time7, time8, time9, time10};
        names = new ArrayList<>();
        times = new ArrayList<>();

        level1 = (Button)v.findViewById(R.id.lvl1ScoreBtn);
        level2 = (Button)v.findViewById(R.id.lvl2ScoreBtn);
        level3 = (Button)v.findViewById(R.id.lvl3ScoreBtn);

        frameScores = (FrameLayout)v.findViewById(R.id.frameScores);

        if (names.size() == 0 && times.size() == 0) {
            fillCards("Level1");
        }

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillCards("Level1");
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillCards("Level2");
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillCards("Level3");
            }
        });

        return v;
    }

    public void fillCards(String level) {
//        SQLiteDatabase database = db.getWritableDatabase();
        names = db.nameHighscore(level);
        times = db.timeHighscore(level);

        name1.setText("");
        name2.setText("");
        name3.setText("");
        name4.setText("");
        name5.setText("");
        name6.setText("");
        name7.setText("");
        name8.setText("");
        name9.setText("");
        name10.setText("");

        time1.setText("");
        time2.setText("");
        time3.setText("");
        time4.setText("");
        time5.setText("");
        time6.setText("");
        time7.setText("");
        time8.setText("");
        time9.setText("");
        time10.setText("");

        if (names.size() >= 10) {
            name1.setText(names.get(0));
            name2.setText(names.get(1));
            name3.setText(names.get(2));
            name4.setText(names.get(3));
            name5.setText(names.get(4));
            name6.setText(names.get(5));
            name7.setText(names.get(6));
            name8.setText(names.get(7));
            name9.setText(names.get(8));
            name10.setText(names.get(9));

            time1.setText(times.get(0));
            time2.setText(times.get(1));
            time3.setText(times.get(2));
            time4.setText(times.get(3));
            time5.setText(times.get(4));
            time6.setText(times.get(5));
            time7.setText(times.get(6));
            time8.setText(times.get(7));
            time9.setText(times.get(8));
            time10.setText(times.get(9));
        }

        if (names.size() < 10) {
            int size = names.size();
            for (int i = 0; i < size; i++) {
                namesView[i].setText(names.get(i));
                timesView[i].setText(times.get(i));
            }
        }
    }
}
