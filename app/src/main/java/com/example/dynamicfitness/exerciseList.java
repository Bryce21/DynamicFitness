package com.example.dynamicfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class exerciseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);


        String arrayToGrab = getIntent().getStringExtra("name");
        int arrayResource = getResources().getIdentifier(
                arrayToGrab, "array", exerciseList.this.getPackageName()
        );
        String[] exerciseList = getResources().getStringArray(arrayResource);


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exerciseList);

        ListView listView = (ListView)findViewById(R.id.exerciseListView);
        listView.setAdapter(adapter);

    }
}
