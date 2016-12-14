package com.example.firdhan.wakkenenijsberen;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Robin T on 9-12-2016.
 */


public class Stap4 extends Fragment {

    private Button back;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stap4, container, false);
        //* custom font voor de uitleg screen \\*
        Typeface iceFont = Typeface.createFromAsset(getActivity().getAssets(), "grandice_regular.ttf");
        back = (Button) rootView.findViewById(R.id.uitlegTerugButton);
        back.setTypeface(iceFont);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return rootView;
    }
}
