package com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.company.xpertech.xpertech.R;
import com.company.xpertech.xpertech.Method.Troubleshoot;

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
    MyTroubleshootRecyclerViewAdapter mAdapter;

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

    public void creatList(){
        troubleshootTitle = new ArrayList<String>();
        troubleshootList = new ArrayList<Troubleshoot>();

        // list of titles
        troubleshootTitle.add("Set Top Box (STB) Configuration");
        troubleshootTitle.add("TV Configuration (via Simple Set Button)");
        troubleshootTitle.add("My set top box (STB) is not Booting Up");
        troubleshootTitle.add("My TV has No Audio and/or Video Output");
        troubleshootTitle.add("My TV is Showing \"Technical Problem\" Error/Pixilated Pictures/ON and OFF Programming");
        troubleshootTitle.add("My TV Screen is Showing an Error Code - E1 / E2 / E11 / E4 / E6 / E14");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Troubleshoot");

        final View view = inflater.inflate(R.layout.fragment_troubleshoot_list, container, false);

        creatList();

        for (int i = 0; i < troubleshootTitle.size(); i++){
            Troubleshoot trbl = new Troubleshoot(troubleshootTitle.get(i));
            troubleshootList.add(trbl);
        }

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mAdapter = new MyTroubleshootRecyclerViewAdapter(troubleshootList,mListener);
        recyclerView.setAdapter(mAdapter);

        EditText editText = (EditText) view.findViewById(R.id.search_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


        return view;
    }

    public void filter(String text){
        ArrayList<Troubleshoot> filtered = new ArrayList<>();
        for(Troubleshoot item: troubleshootList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filtered.add(item);
            }
        }

        mAdapter.filterList(filtered);
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
