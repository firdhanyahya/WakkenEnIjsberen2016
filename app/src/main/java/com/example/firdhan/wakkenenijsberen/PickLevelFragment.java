package com.example.firdhan.wakkenenijsberen;

import android.app.Fragment;
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

public class PickLevelFragment extends Fragment {

    Button level1;
    Button level2;
    Button level3;
    FrameLayout mainFrame;

    public PickLevelFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pick_level_layout, container, false);
        level1 = (Button)v.findViewById(R.id.lvl1Btn);
        level2 = (Button)v.findViewById(R.id.lvl2Btn);
        level3 = (Button)v.findViewById(R.id.lvl3Btn);
        mainFrame = (FrameLayout)v.findViewById(R.id.mainFrameLayout);

        return v;
    }
}
