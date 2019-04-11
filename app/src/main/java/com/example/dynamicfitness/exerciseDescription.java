package com.example.dynamicfitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class exerciseDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_description);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exerciseName");

        TextView textView = (TextView) findViewById(R.id.exerciseDescription);
        textView.setText(exerciseName);
    }
}
