package com.example.dynamicfitness;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class exerciseDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_description);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exerciseName");

        WebView webView = (WebView) findViewById(R.id.WebView01);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setBuiltInZoomControls(true);
        // What to look for
        String videoName = exerciseName + "_"+"video";
        // Id of what you want
        int stringId = getResources().getIdentifier(videoName, "string", exerciseDescription.this.getPackageName());
        // Actually what you want
        String uTubeLink = getResources().getString(stringId);
        webView.loadUrl(uTubeLink);

    }
}
