package com.example.firdhan.wakkenenijsberen.GameManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.firdhan.wakkenenijsberen.LevelPassed;
import com.example.firdhan.wakkenenijsberen.MainActivity;
import com.example.firdhan.wakkenenijsberen.PrefManager;
import com.example.firdhan.wakkenenijsberen.R;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import java.util.Timer;
import java.util.TimerTask;

public class GameLevel2 extends AppCompatActivity {

    //<editor-fold desc="Velden">
    //Settings
    private Timer gameTimer = new Timer();
    private PrefManager prefsManagers;
    private boolean showTimer;
    private boolean showPenguins;
    private int dicesCount;

    //Verander de dice TextViews naar Imageview
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
    private int tries = 0;

    String alertWak = "1.Dobbelsteen 1, 2 en 3 hebben geen wak. Dobbelsteen 4, 5 en 6 hebben 1 wak.";
    String alertIJsbeer = "2.Aantal ijsberen is gelijk aan (aantal ogen dobbelsteen / 2). Oneven dobbelstenen hebben geen ijsberen";
    String alertPeng = "3.De pinguins zijn de ogen aan de achterkant van de dobbelsteen, alleen dobbelsteen 3 en 5 hebben geen penguins.";


    ImageButton help, pause;
    String name;
    ShareLinkContent content;
    TextView level_number;

    //Dialog playername input
    private EditText input;
    int seconds;
    int minutes;

    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_game_level1);
        level_number = (TextView) findViewById(R.id.textView_level);
        //Custom font
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        level_number.setTypeface(iceFont);
        level_number.setText("Level 2");

        //Haal alle settings die de gebruiker heeft gekozen
        this.prefsManagers = new PrefManager(this);
        showTimer = prefsManagers.getTimerSetting();
        showPenguins = prefsManagers.getPenguinsSetting();
        dicesCount = Integer.parseInt(prefsManagers.getDicesSetting().toString());
        //<editor-fold desc="Initialiseren van ImageViews en EditText">
        dice1Img = (ImageView) findViewById(R.id.dice1ImageView);
        dice2Img = (ImageView) findViewById(R.id.dice2ImageView);
        dice3Img = (ImageView) findViewById(R.id.dice3ImageView);
        dice4Img = (ImageView) findViewById(R.id.dice4ImageView);
        dice5Img = (ImageView) findViewById(R.id.dice5ImageView);
        dice6Img = (ImageView) findViewById(R.id.dice6ImageView);
        dice7Img = (ImageView) findViewById(R.id.dice7ImageView);
        dice8Img = (ImageView) findViewById(R.id.dice8ImageView);
        images = new ImageView[]{dice1Img, dice2Img, dice3Img, dice4Img, dice5Img, dice6Img, dice7Img, dice8Img};

        wakken = (EditText) findViewById(R.id.editText);
        ijsberen = (EditText) findViewById(R.id.editText2);
        penguinsTextView = (TextView) findViewById(R.id.game_penguinTextView);
        penguins = (EditText) findViewById(R.id.editText3);
        checkAnswerButton = (Button) findViewById(R.id.button);
        checkAnswerButton.setTypeface(iceFont);
        timerTextView = (TextView) findViewById(R.id.timerTxt);

        if (!showPenguins) {
            penguinsTextView.setVisibility(View.INVISIBLE);
            penguins.setVisibility(View.INVISIBLE);
        }
        //</editor-fold>
        //Haal aantal dobbelstenen uit sharePref
        //Aantal dobbelstenen aangeven en werpen
        //Sla het antwoord op in een array
        // en haal alle geworpen dobbelstenen
        level2.throwDice(dicesCount);
        dices = level2.getDices();
        answers = level2.getAnswer();

        //Timer zodra het spel begint
        startGameTimer();

        //<editor-fold desc="Laat geworpen dobbelstenen zien met fotos">
        for (int i = 0; i < dices.length; i++) {
            String imageName = "dice" + dices[i];
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            images[i].setImageResource(resID);
        }
        //</editor-fold>

        //<editor-fold desc="Help Button">
        help = (ImageButton) findViewById(R.id.helpBtn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHints();
            }
        });
        //</editor-fold>

        pause = (ImageButton) findViewById(R.id.pause_button);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameTimer.cancel();
                pauseGame();
            }
        });

        //<editor-fold desc="OnClicktListener voor het checken van antwoord">
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Als de gebruiker kiest om de penguins ook mee te tellen
                if (!showPenguins) {
                    //Check of de EditText niet leeg zijn.
                    if (!wakken.getText().toString().trim().isEmpty()
                            && !ijsberen.getText().toString().trim().isEmpty()) {
                        //Check of het ingevoerde antwoord juist is
                        if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                                Integer.parseInt(ijsberen.getText().toString()) == answers[1]) {
                            //Als het juist is stop de tijd en laat dialog zien
                            gameTimer.cancel();
                            askPlayerName();
                        } else {
                            tries++;
                            if(tries >= 5 && tries % 5 ==0){
                                showDialogHints();
                            }
                            Toast.makeText(GameLevel2.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //Check of de EditText niet leeg zijn.
                    if (!wakken.getText().toString().trim().isEmpty()
                            && !ijsberen.getText().toString().trim().isEmpty()
                            && !penguins.getText().toString().trim().isEmpty()) {
                        //Check of het ingevoerde antwoord juist is
                        if (Integer.parseInt(wakken.getText().toString()) == answers[0] &&
                                Integer.parseInt(ijsberen.getText().toString()) == answers[1] &&
                                Integer.parseInt(penguins.getText().toString()) == answers[2]) {
                            gameTimer.cancel();
                            askPlayerName();
                        } else {
                            tries++;
                            if(tries >= 5 && tries % 5 ==0){
                                showDialogHints();
                            }
                            Toast.makeText(GameLevel2.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
        //</editor-fold>
    }

    //Methode voor het laten tellen van de tijd van het spel.
    public void startGameTimer() {
        if (showTimer) {
            gameTimer.schedule(new TimerTask() {
                public void run() {
                    try {
                        timeInSecs++;
                        seconds = timeInSecs % 60;
                        minutes = (timeInSecs % 3600) / 60;
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
                    seconds = timeInSecs % 60;
                    minutes = (timeInSecs % 3600) / 60;
                        timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
                        timerTextView.setVisibility(View.INVISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, 0, 1000); // 1 sec
        }
    }

    //Methode voor de dialog wanneer de speler het goede antwoord invoert
    public void askPlayerName() {
        //* custom font \\*
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");
        final Dialog dialog = new Dialog(GameLevel2.this);
        dialog.setContentView(R.layout.ask_playername_dialog);
        dialog.setCancelable(false);
        //<editor-fold desc="timeplayed en header TextViews">
        TextView timeplayertv = (TextView) dialog.findViewById(R.id.timeplayedTV);
        TextView enterplayertv = (TextView) dialog.findViewById(R.id.enterPlayerName);
        TextView header = (TextView) dialog.findViewById(R.id.dialog_info);
        header.setTypeface(iceFont);
        timeplayertv.setTypeface(iceFont);
        enterplayertv.setTypeface(iceFont);
        timeplayertv.setText(getString(R.string.timeplayed) + " " + String.format("%02d:%02d", minutes, seconds));
        //</editor-fold>
        input = (EditText) dialog.findViewById(R.id.playerNameEditText);
        input.setHint("Player_1");

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = input.getText().toString();
                ShareButton shareButton = (ShareButton) dialog.findViewById(R.id.dialog_share);
                content = new ShareLinkContent.Builder()
                        .setContentTitle(name + " Heeft een level gehaald in Wakken en IJsberen")
                        .setContentDescription(String.format("%02d:%02d", minutes, seconds) + "in Wakken en IJsberen")
                        .setContentUrl(Uri.parse("https://dl.dropboxusercontent.com/u/10633539/Gold_Award.PNG")).build();
                shareButton.setShareContent(content);
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = input.getText().toString();
                ShareButton shareButton = (ShareButton) dialog.findViewById(R.id.dialog_share);
                content = new ShareLinkContent.Builder()
                        .setContentTitle(name + " Heeft een level gehaald in Wakken en IJsberen")
                        .setContentDescription(String.format("%02d:%02d", minutes, seconds) + "in Wakken en IJsberen")
                        .setContentUrl(Uri.parse("https://dl.dropboxusercontent.com/u/10633539/Gold_Award.PNG")).build();
                shareButton.setShareContent(content);
            }
        });
        //<editor-fold desc="Submit Button">
        Button submitButton = (Button) dialog.findViewById(R.id.dialog_submit);
        submitButton.setTypeface(iceFont);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kijk eerst of de naam text edit niet leeg is.
                if (input.getText().toString().trim().isEmpty()) {
                    input.setHint(getString(R.string.playernamehere));
                } else {
                    playerNameAndScore = new Highscores(input.getText().toString(), timeInSecs);
                    boolean insertScoreToDatabase = weiDatabase.insertData(playerNameAndScore.getPlayerName()
                            , playerNameAndScore.getTimeInSeconds(), "Level2");
                    if (insertScoreToDatabase) {
                        finish();
                        Toast.makeText(GameLevel2.this, getString(R.string.succesAdded), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(GameLevel2.this, LevelPassed.class);
                        i.putExtra("name", name);
                        i.putExtra("timer", String.format("%02d:%02d", minutes, seconds));
                        startActivity(i);
                    } else {
                        Toast.makeText(GameLevel2.this, getString(R.string.errorAdding), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="Next Level Button">
        Button nextLevel = (Button) dialog.findViewById(R.id.dialog_nextlevel);
        nextLevel.setTypeface(iceFont);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kijk eerst of de naam text edit niet leeg is.
                if (input.getText().toString().trim().isEmpty()) {
                    input.setHint("Please Enter Your name");
                } else {
                    //Finish() zorgt ervoor dat als de gebruiker op back button klikt
                    //dat de GameLevel2 activity niet meer getoond wordt
                    playerNameAndScore = new Highscores(input.getText().toString(), timeInSecs);
                    boolean insertScoreToDatabase = weiDatabase.insertData(playerNameAndScore.getPlayerName()
                            , playerNameAndScore.getTimeInSeconds(), "Level2");
                    if (insertScoreToDatabase) {
                        Toast.makeText(GameLevel2.this, getString(R.string.succesAdded), Toast.LENGTH_SHORT).show();
                        finish();
                        Intent i = new Intent(GameLevel2.this, GameLevel2.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(GameLevel2.this, getString(R.string.errorAdding), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        //</editor-fold>
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.show();
    }

    public void showDialogHints(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameLevel2.this);
        builder1.setTitle("Wakken en IJsberen hulp");
        builder1.setMessage(alertWak + "\n" + alertIJsbeer + "\n" + alertPeng);
        builder1.setCancelable(false); //kan niet buiten de dialog klikken
        builder1.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //<editor-fold desc="Dialog voor het pauzeren van het spel">
    public void pauseGame(){
        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");
        final Dialog dialog = new Dialog(GameLevel2.this);
        dialog.setContentView(R.layout.pause_menu_dialog);
        dialog.setCancelable(false);
        TextView pause_time = (TextView) dialog.findViewById(R.id.pause_timeTv);
        TextView pauseText = (TextView) dialog.findViewById(R.id.pauseTextView);

        pauseText.setTypeface(iceFont);
        pause_time.setTypeface(iceFont);
        pause_time.setText(String.format("%02d:%02d", minutes, seconds));

        Button mainmenu, continuegame, nextLevel;
        mainmenu = (Button) dialog.findViewById(R.id.pause_MainMenuButton);
        continuegame = (Button) dialog.findViewById(R.id.pause_continueButton);
        nextLevel = (Button) dialog.findViewById(R.id.pause_nextLevelButton);

        mainmenu.setTypeface(iceFont);
        continuegame.setTypeface(iceFont);
        nextLevel.setTypeface(iceFont);
        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(GameLevel2.this, MainActivity.class);
                startActivity(i);
            }
        });

        continuegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameTimer = new Timer();
                startGameTimer();
                dialog.cancel();
            }
        });

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(GameLevel2.this, GameLevel3.class);
                startActivity(i);
            }
        });
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.show();

    }
    //</editor-fold>
}
