package com.example.dynamicfitness;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentLevelAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final List<String> mListDataHeader;
    private final Map<String, List<String>> mListData_SecondLevel_Map;
    private final Map<String, List<String>> mListData_ThirdLevel_Map;

    public ParentLevelAdapter(Context mContext, List<String> mListDataHeader) {
        this.mContext = mContext;
        this.mListDataHeader = new ArrayList<>();
        this.mListDataHeader.addAll(mListDataHeader);
        for(String s: mListDataHeader){
            Log.v("parentInfo: ", s);
        }
        // Init second level data
        String[] mItemHeaders;
        mListData_SecondLevel_Map = new HashMap<>();
        int parentCount = mListDataHeader.size();
        mItemHeaders = mContext.getResources().getStringArray(R.array.items_array_expandable_other_child);
        for (int i = 0; i < parentCount; i++) {
            mListData_SecondLevel_Map.put(mListDataHeader.get(i), Arrays.asList(mItemHeaders));
        }
        // THIRD LEVEL
        String[] mItemChildOfChild;
        List<String> listChild;
        mListData_ThirdLevel_Map = new HashMap<>();
        for (Object o : mListData_SecondLevel_Map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object object = entry.getValue();
            Log.v("entry:", entry.toString());
            Log.v("object", object.toString());
            if (object instanceof List) {
                List<String> stringList = new ArrayList<>();
                Collections.addAll(stringList, (String[]) ((List) object).toArray());
                for (int i = 0; i < stringList.size(); i++) {
                    Log.v("stringList", (String)stringList.get(i));
                    String childOfChildArrayName = this.generateArrayName(entry.getKey().toString(), (String)stringList.get(i));
                    Log.v("ChildOfChild", childOfChildArrayName);
                    int arrayResource = mContext.getResources().getIdentifier(childOfChildArrayName, "array", mContext.getPackageName());
                    mItemChildOfChild= mContext.getResources().getStringArray(arrayResource);
                    listChild = Arrays.asList(mItemChildOfChild);
                    mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                }
            }
        }
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final CustomExpListView secondLevelExpListView = new CustomExpListView(this.mContext);
        String parentNode = (String) getGroup(groupPosition);

        secondLevelExpListView.setAdapter(new SecondLevelAdapter(this.mContext, mListData_SecondLevel_Map.get(parentNode), mListData_ThirdLevel_Map, parentNode));
        secondLevelExpListView.setGroupIndicator(null);
        return secondLevelExpListView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drawer_list_group, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setTextColor(Color.CYAN);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private String generateArrayName(String ParentName, String ChildName){
        return ParentName + "_" + ChildName;
    }
}
