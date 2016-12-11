package com.example.firdhan.wakkenenijsberen;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class LevelPassed extends AppCompatActivity {

    ShareButton shareFbBtn;
    String name, timer;
    int level, tries;
    TextView txvwCongrats, scoreTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_level_passed);

        txvwCongrats = (TextView)findViewById(R.id.txtCongrats);
        scoreTimer = (TextView)findViewById(R.id.txtScore);

        //Code voor de 'Share on Facebook-functie'
        shareFbBtn = (ShareButton)findViewById(R.id.fbBtn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle(name + " Heeft een level gehaald in Wakken en IJsberen")
                .setContentDescription(scoreTimer.getText().toString() + "in Wakken en IJsberen")
                .setContentUrl(Uri.parse("https://dl.dropboxusercontent.com/u/10633539/Gold_Award.PNG")).build();
        shareFbBtn.setShareContent(content);
    }
}
