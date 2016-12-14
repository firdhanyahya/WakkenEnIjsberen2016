package com.example.firdhan.wakkenenijsberen;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private TextView penguinText, timerText, languageText, dicesText;
    private Button back;
    private Switch penguins, timer;
    private Spinner dices;
    private PrefManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Typeface iceFont = Typeface.createFromAsset(getAssets(), "grandice_regular.ttf");

        penguinText = (TextView)findViewById(R.id.penguinsTxt);
        timerText = (TextView)findViewById(R.id.timerTxt);
        dicesText = (TextView)findViewById(R.id.dicesTxt);
        back = (Button) findViewById(R.id.settingsBackButton);

        penguinText.setTypeface(iceFont);
        timerText.setTypeface(iceFont);
        dicesText.setTypeface(iceFont);
        back.setTypeface(iceFont);

        penguins = (Switch) findViewById(R.id.penguinSwitch);
        timer = (Switch) findViewById(R.id.timerSwitch);

        dices = (Spinner) findViewById(R.id.dicesSpinner);

        ArrayAdapter<CharSequence> diceCountAdapter = ArrayAdapter.createFromResource(this,
                R.array.how_many_dices_spinner, android.R.layout.simple_list_item_activated_1);
        dices.setAdapter(diceCountAdapter);

        manager = new PrefManager(this);

        //Haal settings uit sharedpreference
        boolean boolPenguins = manager.getPenguinsSetting();
        boolean boolTimer = manager.getTimerSetting();
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
