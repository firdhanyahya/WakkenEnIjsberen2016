package com.example.firdhan.wakkenenijsberen;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private TextView penguinText, timerText, languageText, dicesText;
    private Switch penguins, timer;
    private Spinner language, dices;
    private PrefManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        penguinText = (TextView)findViewById(R.id.penguinsTxt);
        timerText = (TextView)findViewById(R.id.timerTxt);
        languageText = (TextView)findViewById(R.id.languageTxt);
        dicesText = (TextView)findViewById(R.id.dicesTxt);

        penguinText.setTypeface(iceFont);
        timerText.setTypeface(iceFont);
        languageText.setTypeface(iceFont);
        dicesText.setTypeface(iceFont);

        penguins = (Switch) findViewById(R.id.penguinSwitch);
        timer = (Switch) findViewById(R.id.timerSwitch);

        language = (Spinner) findViewById(R.id.languageSpinner);
        dices = (Spinner) findViewById(R.id.dicesSpinner);
//
//        //Items voor de spinners
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this,
                R.array.languages_for_spinner, android.R.layout.simple_dropdown_item_1line);
        language.setAdapter(languageAdapter);

        ArrayAdapter<CharSequence> diceCountAdapter = ArrayAdapter.createFromResource(this,
                R.array.how_many_dices_spinner, android.R.layout.simple_list_item_activated_1);
        dices.setAdapter(diceCountAdapter);

        manager = new PrefManager(this);

        //Haal settings uit sharedpreference
        boolean boolPenguins = manager.getPenguinsSetting();
        boolean boolTimer = manager.getTimerSetting();
        String stringLanguage = manager.getLanguageSetting();
        String stringDices = manager.getDicesSetting();

        if(boolPenguins == true) {
            penguins.setChecked(true);
            penguins.setText("On");
        } else {
            penguins.setChecked(false);
            penguins.setText("Off");
        }

        if(boolTimer == true) {
            timer.setChecked(true);
            timer.setText("On");
        } else {
            timer.setChecked(false);
            timer.setText("Off");
        }

        if (!stringLanguage.equals(null)) {
            int languagePosition = languageAdapter.getPosition(stringLanguage);
            language.setSelection(languagePosition);
        }

        if (!stringDices.equals(null)) {
            int dicePosition = diceCountAdapter.getPosition(stringDices);
            dices.setSelection(dicePosition);
        }


        //Switch onCheckChangedListener
        penguins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    penguins.setText("On");
                    manager.editPenguinsSettings(true);
                } else {
                    penguins.setText("Off");
                    manager.editPenguinsSettings(false);
                }
            }
        });

        timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    timer.setText("On");
                    manager.editTimerSettings(true);
                } else {
                    timer.setText("Off");
                    manager.editTimerSettings(false);
                }
            }
        });

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String setting = language.getSelectedItem().toString();
                manager.editLanguageSettings(setting);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String setting = dices.getSelectedItem().toString();
                manager.editDicesSettings(setting);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
