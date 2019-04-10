package com.example.dynamicfitness;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {
    View v;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Arms");
        listDataHeader.add("Neck");
        listDataHeader.add("Shoulders");
        listDataHeader.add("Chest");
        listDataHeader.add("Core");
        listDataHeader.add("Back");
        listDataHeader.add("Thighs");
        listDataHeader.add("Glutes");
        listDataHeader.add("Calves");
        listDataHeader.add("Whole Body");

        // Adding child data
        List<String> arm = new ArrayList<String>();
        arm.add("Beginner");
        arm.add("Intermediate");
        arm.add("Advanced");


        List<String> neck = new ArrayList<String>();
        neck.add("Beginner");
        neck.add("Intermediate");
        neck.add("Advanced");

        List<String> shoulders = new ArrayList<String>();
        shoulders.add("Beginner");
        shoulders.add("Intermediate");
        shoulders.add("Advanced");

        List<String> chest = new ArrayList<String>();
        chest.add("Beginner");
        chest.add("Intermediate");
        chest.add("Advanced");

        List<String> core = new ArrayList<String>();
        core.add("Beginner");
        core.add("Intermediate");
        core.add("Advanced");

        List<String> back = new ArrayList<String>();
        back.add("Beginner");
        back.add("Intermediate");
        back.add("Advanced");

        List<String> thighs = new ArrayList<String>();
        thighs.add("Beginner");
        thighs.add("Intermediate");
        thighs.add("Advanced");

        List<String> glutes = new ArrayList<String>();
        glutes.add("Beginner");
        glutes.add("Intermediate");
        glutes.add("Advanced");

        List<String> calves = new ArrayList<String>();
        calves.add("Beginner");
        calves.add("Intermediate");
        calves.add("Advanced");

        List<String> wholeBody = new ArrayList<String>();
        wholeBody.add("Beginner");
        wholeBody.add("Intermediate");
        wholeBody.add("Advanced");

        listDataChild.put(listDataHeader.get(0), arm); // Header, Child data
        listDataChild.put(listDataHeader.get(1), neck);
        listDataChild.put(listDataHeader.get(2), shoulders);
        listDataChild.put(listDataHeader.get(3), chest);
        listDataChild.put(listDataHeader.get(4), core);
        listDataChild.put(listDataHeader.get(5), back);
        listDataChild.put(listDataHeader.get(6), thighs);
        listDataChild.put(listDataHeader.get(7), glutes);
        listDataChild.put(listDataHeader.get(8), calves);
        listDataChild.put(listDataHeader.get(9), wholeBody);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tab1, container, false);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
