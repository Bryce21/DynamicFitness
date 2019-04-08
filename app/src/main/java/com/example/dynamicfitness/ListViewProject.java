package com.example.dynamicfitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListViewProject extends AppCompatActivity {
    private final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int arrayResource = getResources().getIdentifier("exerciseGroups", "array", ListViewProject.this.getPackageName());
        final String[] exerciseGroups = getResources().getStringArray(arrayResource);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exerciseGroups);


        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent appInfo = new Intent(ListViewProject.this, exerciseList.class);
                appInfo.putExtra("name", exerciseGroups[position]);
                startActivity(appInfo);
            }
        });
    }
}
