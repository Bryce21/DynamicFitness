package com.example.dynamicfitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class BodyPartDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_part_description);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("List Position", 0);

        TextView bodyDescriptions = (TextView)findViewById(R.id.Body_Descriptions);
        bodyDescriptions.setMovementMethod(new ScrollingMovementMethod());

        if (intValue == 0) {
            bodyDescriptions.setText(R.string.arm_description);
        }
        else if (intValue == 1){
            bodyDescriptions.setText(R.string.shoulders_description);
        }
        else if (intValue == 2){
            bodyDescriptions.setText(R.string.chest_description);
        }
        else if (intValue == 3){
            bodyDescriptions.setText(R.string.core_description);
        }
        else if (intValue == 4){
            bodyDescriptions.setText(R.string.back_description);
        }
        else if (intValue == 5){
            bodyDescriptions.setText(R.string.leg_description);
        }
    }
}
