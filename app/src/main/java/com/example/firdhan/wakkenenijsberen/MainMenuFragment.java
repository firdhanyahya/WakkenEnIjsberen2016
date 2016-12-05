package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Firdhan on 02/12/2016.
 */

public class MainMenuFragment extends Fragment {

    Button playBtn;
    Button howToBtn;
    Button settingsBtn;
    Button highscoresBtn;
    Button aboutBtn;
    FrameLayout mainFrame;

    public MainMenuFragment() {
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu_layout, container, false);

        playBtn = (Button)v.findViewById(R.id.playBtn);
        howToBtn = (Button)v.findViewById(R.id.howToBtn);
        settingsBtn = (Button)v.findViewById(R.id.settingsBtn);
        highscoresBtn = (Button)v.findViewById(R.id.highscoresBtn);
        aboutBtn = (Button)v.findViewById(R.id.aboutBtn);
        mainFrame = (FrameLayout)v.findViewById(R.id.mainFrameLayout);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                //Hier fragment replacen
            }
        });


        return v;
    }
}
