package com.example.dynamicfitness;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<ListItem>> _listDataChild;
    public HashMap<String, Boolean> checkedState = new HashMap<String, Boolean>();

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                  HashMap<String, List<ListItem>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ListItem item = (ListItem) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView name;
        name = (TextView) convertView.findViewById(R.id.lblListItem);
        name.setText(item.exerciseName);


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("myLog onClick", item.exerciseName);
                Intent intent = new Intent(_context, YoutubePlayerVideo.class);
                intent.putExtra("exerciseName", item.exerciseName);
                _context.startActivity(intent);
            }
        });


        final CheckBox selected;
        selected = (CheckBox) convertView.findViewById(R.id.toggleButtonSelected);
        boolean value;
        if (checkedState.get(item.exerciseName) == null){
            value = false;
        } else if (checkedState.get(item.exerciseName)){
            value = true;
        } else {
            value = false;
        }
        checkedState.put(item.exerciseName, value);
        selected.setChecked(checkedState.get(item.exerciseName));

        Log.v("buttonLog expandable", "in expandableListAdapter");
        for(String key: checkedState.keySet()){
            Log.v("buttonLogInteresting", "key: "+key+" value: "+checkedState.get(key));
        }


        selected.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v("buttonLog", "Clicked. "+item.exerciseName + " "+ !checkedState.get(item.exerciseName));
                checkedState.put(item.exerciseName, !checkedState.get(item.exerciseName));
                selected.setChecked(checkedState.get(item.exerciseName));
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}