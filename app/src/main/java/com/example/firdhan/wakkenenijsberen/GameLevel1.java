package com.example.firdhan.wakkenenijsberen;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firdhan.wakkenenijsberen.GameLevels.Level1;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameLevel1 extends AppCompatActivity {

    //Settings
    private Timer gameTimer = new Timer();
    private Boolean showTimer;
    private Boolean showPenguins;

    private TextView dice1, dice2, dice3, dice4, dice5, timerTextView;
    private EditText wakken, ijsberen, penguins;
    private Button checkAnswerButton;
    private Level1 level1 = new Level1();

    private int timeInSecs;
    private int[] answers;
    private int[] dices;
    private int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level1);
        //<editor-fold desc="Initialiseren van TextViews">
        //Haal in sharedPref of de speler de tijd wilt zien of niet.
//        this.showTimer =
        dice1 = (TextView)findViewById(R.id.dice1Txt);
        dice2 = (TextView)findViewById(R.id.dice2Txt);
        dice3 = (TextView)findViewById(R.id.dice3Txt);
        dice4 = (TextView)findViewById(R.id.dice4Txt);
        dice5 = (TextView)findViewById(R.id.dice5Txt);
        wakken = (EditText) findViewById(R.id.editText);
        ijsberen = (EditText)findViewById(R.id.editText2);
        penguins = (EditText)findViewById(R.id.editText3);
        checkAnswerButton = (Button) findViewById(R.id.button);
        timerTextView = (TextView) findViewById(R.id.timerTxt);
        //</editor-fold>
        //Haal aantal dobbelstenen uit sharePref
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

        //Tijd van het spel
        gameTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    timeInSecs++;
                    int seconds = timeInSecs % 60;
                    int minutes =  (timeInSecs % 3600) / 60;
                    timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, 0, 1000); // 1 sec

        //OnClicktListener voor het checken van antwoord
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
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
