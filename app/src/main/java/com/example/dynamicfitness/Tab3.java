package com.example.dynamicfitness;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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

        Bitmap armImage;
        armImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.arm);

        Bitmap shoulderImage;
        shoulderImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.shoulders);

        Bitmap chestImage;
        chestImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.chest);

        Bitmap coreImage;
        coreImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.core);

        Bitmap backImage;
        backImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.back);

        Bitmap legImage;
        legImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.leg);

        // Create testing data
        List<ListViewItem> list = new ArrayList<ListViewItem>();
        ListViewItem item1 = new ListViewItem();
        item1.image = armImage;
        item1.name = "Arms";
        item1.comment = "Details about the arms.";
        list.add(item1);

        ListViewItem item2 = new ListViewItem();
        item2.image = shoulderImage;
        item2.name = "Shoulders";
        item2.comment = "Details about the shoulders.";
        list.add(item2);

        ListViewItem item3 = new ListViewItem();
        item3.image = chestImage;
        item3.name = "Chest";
        item3.comment = "Details about the chest.";
        list.add(item3);

        ListViewItem item4 = new ListViewItem();
        item4.image = coreImage;
        item4.name = "Core";
        item4.comment = "Details about the core.";
        list.add(item4);

        ListViewItem item5 = new ListViewItem();
        item5.image = backImage;
        item5.name = "Back";
        item5.comment = "Details about the back.";
        list.add(item5);

        ListViewItem item6 = new ListViewItem();
        item6.image = legImage;
        item6.name = "Legs";
        item6.comment = "Details about the legs.";
        list.add(item6);

        // Create ListItemAdapter
        ListViewItemAdapter adapter;
        adapter = new ListViewItemAdapter(this.getContext(), 0, list);

        // Assign ListItemAdapter to ListView
        ListView listView = (ListView) getActivity().findViewById(R.id.ListView01);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),BodyPartDescription.class);
                intent.putExtra("List Position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false);
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
