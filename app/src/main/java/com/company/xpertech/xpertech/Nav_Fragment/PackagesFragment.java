package com.company.xpertech.xpertech.Nav_Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.xpertech.xpertech.Packages;
import com.company.xpertech.xpertech.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackagesFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    ArrayList <String> packageTitle;
    ArrayList <String> packageChannel;
    ArrayList<Packages> packagesList;

    RecyclerView recyclerView;

    public PackagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PackagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackagesFragment newInstance(int columnCount) {
        PackagesFragment fragment = new PackagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_packages_list, container, false);
        packageTitle = new ArrayList<String>();
        packageChannel = new ArrayList<String>();
        packagesList = new ArrayList<Packages>();

        packageTitle.add("Crystal Package");
        packageTitle.add("Diamond Package");

        packageChannel.add("More than 100 channels");
        packageChannel.add("Crystal Package + 17 Premium Channels");
        for (int i = 0; i < packageTitle.size(); i++){
            Packages packages = new Packages(packageTitle.get(i), packageChannel.get(i));
            packagesList.add(packages);
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new PackagesRecyclerView(packagesList, mListener));
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnListFragmentInteractionListener(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Packages item);
    }
}
