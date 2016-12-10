package com.example.firdhan.wakkenenijsberen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch penguins, timer;
    private Spinner language, diceCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        penguins = (Switch) findViewById(R.id.settings_penguinsSwitch);
        timer = (Switch) findViewById(R.id.settings_timerSwitch);
        //Haal de commentaar wel zodra de layout al goed is
//        language = (Spinner) findViewById(R.id.settings_languageSpinner);
//        diceCount = (Spinner) findViewById(R.id.settings_diceCountSpinner);
//
//        //Items voor de spinners
//        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this,
//                R.array.languages_for_spinner, android.R.layout.simple_dropdown_item_1line);
//        language.setAdapter(languageAdapter);
//
//        ArrayAdapter<CharSequence> diceCountAdapter = ArrayAdapter.createFromResource(this,
//                R.array.how_many_dices_spinner, android.R.layout.simple_list_item_activated_1);
//        diceCount.setAdapter(diceCountAdapter);

        //Switch onCheckChangedListener
        penguins.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    penguins.setText("On");
                } else {
                    penguins.setText("Off");
                }
            }
        });

        timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    timer.setText("On");
                } else {
                    timer.setText("Off");
                }
            }
        });
    }
}
