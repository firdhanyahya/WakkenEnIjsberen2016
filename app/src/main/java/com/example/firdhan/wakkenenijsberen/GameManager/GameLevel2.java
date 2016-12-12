package com.example.firdhan.wakkenenijsberen.GameManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firdhan.wakkenenijsberen.Databases.DBHandler;
import com.example.firdhan.wakkenenijsberen.GameLevels.Level2;
import com.example.firdhan.wakkenenijsberen.Highscores;
import com.example.firdhan.wakkenenijsberen.PrefManager;
import com.example.firdhan.wakkenenijsberen.R;

import java.util.Timer;
import java.util.TimerTask;

public class GameLevel2 extends AppCompatActivity {
    //<editor-fold desc="Velden">
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
    private Level2 level2 = new Level2();

    //Imageviews
    private ImageView dice1Img, dice2Img, dice3Img, dice4Img, dice5Img, dice6Img, dice7Img, dice8Img;
    private ImageView[] images;

    private Highscores playerNameAndScore;
    private DBHandler weiDatabase = new DBHandler(this);

    private int timeInSecs = -1;
    private int[] answers;
    private int[] dices;
    private int tries = 1;

    String alertWak = "1.Wak is het middelste oog van een dobbelsteen.";
    String alertIJsbeer = "2.De ijsberen zijn de ogen om een wak heen.";
    String alertPeng = "3.De pinguins zijn de ogen aan de achterkant van de dobbelsteen, De voorkant en de achterkant van de dobbelsteen zijn altijd samen 7 ogen.";


    ImageButton help;

    //Dialog playername input
    private EditText input;

    //</editor-fold>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_game_level2);
        //Haal alle settings die de gebruiker heeft gekozen
        this.prefsManagers = new PrefManager(this);
        showTimer = prefsManagers.getTimerSetting();
        showPenguins = prefsManagers.getPenguinsSetting();
        //<editor-fold desc="Initialiseren van ImageViews en EditText">
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
        //Aantal dobbelstenen aangeven en werpen
        //Sla het antwoord op in een array
        // en haal alle geworpen dobbelstenen
        level2.throwDice(Integer.parseInt(prefsManagers.getDicesSetting().toString()));
        dices = level2.getDices();
        answers = level2.getAnswer();

        //Timer zodra het spel begint
        startGameTimer();

        for (int i = 0; i < dices.length; i++) {
            String imageName = "dice" + dices[i];
            int resID = getResources().getIdentifier(imageName , "drawable", getPackageName());
            images[i].setImageResource(resID);
        }

        //Help button//
        help = (ImageButton)findViewById(R.id.helpBtn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(GameLevel2.this);
                builder1.setTitle("Wakken en IJsberen hulp");
                builder1.setMessage(alertWak + "\n" + alertIJsbeer + "\n" + alertPeng);
                builder1.setCancelable(false); //kan niet buiten de dialog klikken

                builder1.setPositiveButton(
                        "Begrepen",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        //OnClicktListener voor het checken van antwoord
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showPenguins){
                    if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                            Integer.parseInt(ijsberen.getText().toString()) == answers[1]) {
                        gameTimer.cancel();
                        askPlayerName();
                    } else {
                        tries++;
                        Toast.makeText(GameLevel2.this, "False", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                            Integer.parseInt(ijsberen.getText().toString()) == answers[1] &&
                            Integer.parseInt(penguins.getText().toString()) == answers[2]) {
                        gameTimer.cancel();
                        askPlayerName();
//                        Intent i = new Intent(GameLevel2.this, LevelPassed.class);
//                        startActivity(i);
                    } else {
                        tries++;
                        Toast.makeText(GameLevel2.this, "False", Toast.LENGTH_SHORT).show();
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
        int seconds = timeInSecs % 60;
        int minutes =  (timeInSecs % 3600) / 60;
        input = new EditText(this);
        input.setText("Player_1");
        AlertDialog dialog = new AlertDialog.Builder(GameLevel2.this)
                .setTitle("Well done!")
                .setMessage("Time played: " + String.format("%02d:%02d", minutes, seconds) + "\nEnter player name:")
                .setView(input)
                .setNeutralButton("Share", null)
                .setNegativeButton("Submit", null)
                .setPositiveButton("Next Level", null)
                .setCancelable(false)
                .create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.show();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //* custom font \\*
                Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

                //<editor-fold desc="Share to Facebook button">
                Button shareButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
                shareButton.setTypeface(iceFont);
                shareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Share to facebook functie
                    }
                });
                //</editor-fold>
                //<editor-fold desc="Submit button">
                Button submitButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                submitButton.setTypeface(iceFont);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Sla de naam op en terug naar level picker
                        //Kijk eerst of de naam text edit niet leeg is.
                    }
                });
                //</editor-fold>
                Button nextLevelButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                nextLevelButton.setTypeface(iceFont);
                nextLevelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Sla de naam op en door naar de volgende level
                        //Kijk eerst of de naam text edit niet leeg is.
                    }
                });
            }
        });
    }

    public void testShowData(){
        SQLiteDatabase db = weiDatabase.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from GameHighscores ORDER BY Time", null);
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
