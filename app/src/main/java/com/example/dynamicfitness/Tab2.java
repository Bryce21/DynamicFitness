package com.example.dynamicfitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    View v;
    ExpandableTab2 listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2() {}


    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp2);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableTab2(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        Button clearButton = (Button) v.findViewById(R.id.clearButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            SharedPreferences pref = getContext().getSharedPreferences("savedWorkoutLists", 0);
            SharedPreferences.Editor editor = pref.edit();
            Map<String,?> keys = pref.getAll();
            @Override
            public void onClick(View v) {
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    editor.remove(entry.getKey());
                }
                editor.apply();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tab2, container, false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        SharedPreferences pref = getContext().getSharedPreferences("savedWorkoutLists", 0);
        SharedPreferences.Editor editor = pref.edit();

        Map<String,?> keys = pref.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            if(entry.getKey().contains("workout")){
                listDataHeader.add(entry.getKey());
                List<String> setList = new ArrayList<String>();

                setList.addAll((Set)entry.getValue());

                listDataChild.put(entry.getKey(), setList);
            }

        }
    }
}
