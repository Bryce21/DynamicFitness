package com.example.dynamicfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewItemAdapter extends ArrayAdapter<ListViewItem> {
    private LayoutInflater mInflater;
    public ListViewItemAdapter(Context context, int rid, List<ListViewItem> list){
        super(context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        // Retrieve data
        ListViewItem item = (ListViewItem)getItem(position);

        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.list_item2, null);

        // Set image
        ImageView image;
        image = (ImageView)view.findViewById(R.id.image);
        image.setImageBitmap(item.image);

        // Set user name
        TextView name;
        name = (TextView)view.findViewById(R.id.name);
        name.setText(item.name);

        // Set comment
        TextView comment;
        comment = (TextView) view.findViewById(R.id.comment);
        comment.setText(item.comment);

        return view;
    }


}