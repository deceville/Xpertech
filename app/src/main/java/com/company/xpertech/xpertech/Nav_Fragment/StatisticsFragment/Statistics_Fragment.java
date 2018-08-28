package com.company.xpertech.xpertech.Nav_Fragment.StatisticsFragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.xpertech.xpertech.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Statistics_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Statistics_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statistics_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Statistics_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statistics_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Statistics_Fragment newInstance(String param1, String param2) {
        Statistics_Fragment fragment = new Statistics_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_statistics_, container, false);

        PieChart mChart;
        String[] xValues = {"Successful Login", "Failed Login"};

        mChart = (PieChart)view.findViewById(R.id.login_piechart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(40, 0));
        entries.add(new Entry(60, 0));

        PieDataSet dataSet =  new PieDataSet(entries, "");
        PieData data = new PieData(xValues, dataSet);
        dataSet.setColors(new int[]{Color.BLUE, Color.WHITE});
        dataSet.setSliceSpace(5f);
        dataSet.setValueTextSize(20f);
        mChart.setUsePercentValues(true);
        mChart.setDrawHoleEnabled(false);
        mChart.setData(data);
        mChart.invalidate();

        xValues = new String[]{"Successful Troubleshoot", "Failed Troubleshoot"};
        mChart = (PieChart)view.findViewById(R.id.troubleshoot_piechart);
        entries = new ArrayList<>();
        entries.add(new Entry(80, 0));
        entries.add(new Entry(20, 0));
        dataSet =  new PieDataSet(entries, "");
        data = new PieData(xValues, dataSet);
        dataSet.setColors(new int[]{Color.CYAN, Color.WHITE});
        dataSet.setSliceSpace(5f);
        dataSet.setValueTextSize(10f);
        mChart.setUsePercentValues(true);
        mChart.setDrawHoleEnabled(false);
        mChart.setData(data);
        mChart.invalidate();

        return view;
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
