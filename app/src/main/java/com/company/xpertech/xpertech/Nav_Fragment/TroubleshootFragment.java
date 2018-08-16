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

import com.company.xpertech.xpertech.R;
import com.company.xpertech.xpertech.Troubleshoot;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TroubleshootFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    ArrayList <String> troubleshootTitle;
    ArrayList <Troubleshoot> troubleshootList;

    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TroubleshootFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TroubleshootFragment newInstance(int columnCount) {
        TroubleshootFragment fragment = new TroubleshootFragment();
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

        final View view = inflater.inflate(R.layout.fragment_troubleshoot_list, container, false);

        troubleshootTitle = new ArrayList<String>();
        troubleshootList = new ArrayList<Troubleshoot>();

        // list of titles
        troubleshootTitle.add("Set Top Box (STB) Configuration");
        troubleshootTitle.add("TV Configuration (via Simple Set Button)");
        troubleshootTitle.add("My set top box (STB) is not Booting Up");
        troubleshootTitle.add("My TV has No Audio and/or Video Output");
        troubleshootTitle.add("My TV is Showing \"Technical Problem\" Error/Pixilated Pictures/ON and OFF Programming");
        troubleshootTitle.add("My TV Screen is Showing an Error Code - E1 / E2 / E11 / E4 / E6 / E14");

        for (int i = 0; i < troubleshootTitle.size(); i++){
            Troubleshoot trbl = new Troubleshoot(troubleshootTitle.get(i));
            troubleshootList.add(trbl);
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
            recyclerView.setAdapter(new MyTroubleshootRecyclerViewAdapter(troubleshootList, mListener));
        }


//        view.setOnClickListener((new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int itemPosition = recyclerView.getChildAdapterPosition(view);
//                        String item = troubleshootTitle.get(itemPosition);
//                        Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
//                    }
//                }));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Troubleshoot item);
    }
}
