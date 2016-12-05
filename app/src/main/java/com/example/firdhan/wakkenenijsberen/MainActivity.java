package com.example.firdhan.wakkenenijsberen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.firdhan.wakkenenijsberen.GameLevels.Level1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Level1 test = new Level1();
        test.throwDice(3);
        int[] resultTest = test.getAnswer();
        int[] dices = test.getDices();
    }
}
