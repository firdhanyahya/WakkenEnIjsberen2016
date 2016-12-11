package com.example.firdhan.wakkenenijsberen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firdhan.wakkenenijsberen.Databases.DatabaseHandler;
import com.example.firdhan.wakkenenijsberen.GameLevels.Level1;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameLevel1 extends AppCompatActivity {

    //Settings
    private Timer gameTimer = new Timer();
    private PrefManager prefsManagers;
    private Boolean showTimer;
    private Boolean showPenguins;

    //Verander de dice TextViews naar Imageview
//    private TextView dice1, dice2, dice3, dice4, dice5, timerTextView, penguinsTextView;
    private TextView timerTextView, penguinsTextView;
    private EditText wakken, ijsberen, penguins;
    private Button checkAnswerButton;
    private Level1 level1 = new Level1();

    //Imageviews
    private ImageView dice1Img, dice2Img, dice3Img, dice4Img, dice5Img, dice6Img, dice7Img, dice8Img;
    private ImageView[] images;

    private Highscores playerNameAndScore;
    private DatabaseHandler weiDatabase = new DatabaseHandler(this);

    private int timeInSecs = -1;
    private int[] answers;
    private int[] dices;
    private int tries = 1;

    //Dialog playername input
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_game_level1);
        testShowData();
        this.prefsManagers = new PrefManager(this);
        showTimer = prefsManagers.getTimerSetting();
        showPenguins = prefsManagers.getPenguinsSetting();
        //<editor-fold desc="Initialiseren van TextViews">
        //Haal in sharedPref of de speler de tijd wilt zien of niet.
//        dice1 = (TextView)findViewById(R.id.dice1Txt);
//        dice2 = (TextView)findViewById(R.id.dice2Txt);
//        dice3 = (TextView)findViewById(R.id.dice3Txt);
//        dice4 = (TextView)findViewById(R.id.dice4Txt);
//        dice5 = (TextView)findViewById(R.id.dice5Txt);

        dice1Img = (ImageView)findViewById(R.id.dice1ImageView);
        dice2Img = (ImageView)findViewById(R.id.dice2ImageView);
        dice3Img = (ImageView)findViewById(R.id.dice3ImageView);
        dice4Img = (ImageView)findViewById(R.id.dice4ImageView);
        dice5Img = (ImageView)findViewById(R.id.dice5ImageView);
        dice6Img = (ImageView)findViewById(R.id.dice6ImageView);
        dice7Img = (ImageView)findViewById(R.id.dice7ImageView);
        dice8Img = (ImageView)findViewById(R.id.dice8ImageView);
        images  = new ImageView[] {dice1Img, dice2Img, dice3Img, dice4Img, dice5Img, dice6Img, dice7Img, dice8Img};

        wakken = (EditText) findViewById(R.id.editText);
        ijsberen = (EditText)findViewById(R.id.editText2);
        penguinsTextView = (TextView) findViewById(R.id.game_penguinTextView);
        penguins = (EditText)findViewById(R.id.editText3);
        checkAnswerButton = (Button) findViewById(R.id.button);
        timerTextView = (TextView) findViewById(R.id.timerTxt);

        if(!showPenguins){
            penguinsTextView.setVisibility(View.INVISIBLE);
            penguins.setVisibility(View.INVISIBLE);
        }
        //</editor-fold>
        //Haal aantal dobbelstenen uit sharePref
        level1.throwDice(Integer.parseInt(prefsManagers.getDicesSetting().toString()));
        dices = level1.getDices();
        answers = level1.getAnswer();
        startGameTimer();
        
//        if(dices != null) {
//                dice1.setText(Integer.toString(dices[0]));
//                dice2.setText(Integer.toString(dices[1]));
//                dice3.setText(Integer.toString(dices[2]));
//
//        }
        for (int i = 0; i < dices.length; i++) {
            String imageName = "dice" + dices[i];
            int resID = getResources().getIdentifier(imageName , "drawable", getPackageName());
            images[i].setImageResource(resID);
        }

        //OnClicktListener voor het checken van antwoord
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //<editor-fold desc="Test voor het pauzeren van het spel">
//                tries++;
//                if(tries % 2 == 0){
//                    gameTimer.cancel();
//                    Toast.makeText(GameLevel1.this, "biatch is gestopt", Toast.LENGTH_LONG).show();
//                } else {
//                    gameTimer = new Timer();
//                    startGameTimer();
//                }
             //</editor-fold>
                if(!showPenguins){
                    if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                            Integer.parseInt(ijsberen.getText().toString()) == answers[1]) {
                        gameTimer.cancel();
                        askPlayerName();
                    } else {
                        tries++;
                        Toast.makeText(GameLevel1.this, "False", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                            Integer.parseInt(ijsberen.getText().toString()) == answers[1] &&
                            Integer.parseInt(penguins.getText().toString()) == answers[2]) {
                        gameTimer.cancel();
                        askPlayerName();
                    } else {
                        tries++;
                        Toast.makeText(GameLevel1.this, "False", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    //Methode voor het laten tellen van de tijd van het spel.
    public void startGameTimer(){
        if(showTimer){
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
        } else {
            gameTimer.schedule(new TimerTask() {
                public void run() {
                    try {
                        timeInSecs++;
                        int seconds = timeInSecs % 60;
                        int minutes =  (timeInSecs % 3600) / 60;
                        timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
                        timerTextView.setVisibility(View.INVISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, 0, 1000); // 1 sec
        }
    }

    public void askPlayerName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well done!");
        builder.setMessage("Enter Player Name:");
        input = new EditText(this);
        input.setText("Player_1");
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playerNameAndScore = new Highscores(input.getText().toString(), timeInSecs);
                boolean addPlayerToDatabase = weiDatabase.insertData(playerNameAndScore.getPlayerName(), playerNameAndScore.getTimeInSeconds());
                if(addPlayerToDatabase){
                    finish();
                } else {
                    Toast.makeText(GameLevel1.this, "Error by adding player to the database", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id

        dialog.show();
    }

    public void testShowData(){
        SQLiteDatabase db = weiDatabase.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from WeIHighscores ORDER BY Time", null);
        if(res.getCount() == 0){
            Toast.makeText(this, "nothing found", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("ID :" + res.getInt(0) + "\n");
                buffer.append("PlayerName :" + res.getString(1) + "\n");
                buffer.append("Time played :" + res.getString(2) + "\n\n");
            }
            Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
