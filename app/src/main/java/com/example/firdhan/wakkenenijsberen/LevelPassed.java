package com.example.firdhan.wakkenenijsberen;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class LevelPassed extends AppCompatActivity {

    String name, timer;
    TextView txvwCongrats, scoreTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_passed);

        txvwCongrats = (TextView)findViewById(R.id.txtCongrats);
        scoreTimer = (TextView)findViewById(R.id.txtCongrats);
    }
}
