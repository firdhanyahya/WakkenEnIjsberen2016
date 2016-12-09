package com.example.firdhan.wakkenenijsberen;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firdhan.wakkenenijsberen.GameLevels.Level1;

public class GameLevel1 extends AppCompatActivity {

    TextView dice1;
    TextView dice2;
    TextView dice3;
    TextView dice4;
    TextView dice5;
    EditText wakken;
    EditText ijsberen;
    EditText penguins;
    Level1 level1;
    Button b;
    int[] answers;
    int[] dices;
    int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level1);
        dice1 = (TextView)findViewById(R.id.dice1Txt);
        dice2 = (TextView)findViewById(R.id.dice2Txt);
        dice3 = (TextView)findViewById(R.id.dice3Txt);
        dice4 = (TextView)findViewById(R.id.dice4Txt);
        dice5 = (TextView)findViewById(R.id.dice5Txt);
        wakken = (EditText) findViewById(R.id.editText);
        ijsberen = (EditText)findViewById(R.id.editText2);
        penguins = (EditText)findViewById(R.id.editText3);
        b = (Button) findViewById(R.id.button);
        
        level1 = new Level1();
        level1.throwDice(5);
        dices = level1.getDices();
        answers = level1.getAnswer();
        
        if(dices != null) {
            dice1.setText(Integer.toString(dices[0]));
            dice2.setText(Integer.toString(dices[1]));
            dice3.setText(Integer.toString(dices[2]));
            dice4.setText(Integer.toString(dices[3]));
            dice5.setText(Integer.toString(dices[4]));
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                        Integer.parseInt(ijsberen.getText().toString()) == answers[1] &&
                        Integer.parseInt(penguins.getText().toString()) == answers[2]) {
                    Toast.makeText(GameLevel1.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    tries++;
                    Toast.makeText(GameLevel1.this, "False", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
