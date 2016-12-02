package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Firdhan on 02/12/2016.
 */

public class MainMenuFragment extends Fragment {
    public MainMenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu_layout, container, false);
        return v;
    }
}
