package com.company.xpertech.xpertech.Nav_Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.company.xpertech.xpertech.Channels;
import com.company.xpertech.xpertech.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChannelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private List<String> crystal_channelTitle;
    private List<String> diamond_channelTitle;
    private List<Integer> crystal_channelNumber;
    private List<Integer> diamond_channelNumber;
    private List<Channels> crystal_channel_list;
    private List<Channels> diamond_channel_list;

    private RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;

    public ChannelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance(String param1, String param2) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_channel_list, container, false);

        Bundle bundle = getArguments();
        String packages = bundle.getString("package");


        crystal_channelTitle = new ArrayList<String>();
        diamond_channelTitle = new ArrayList<String>();
        crystal_channelNumber = new ArrayList<Integer>();
        diamond_channelNumber = new ArrayList<Integer>();
        crystal_channel_list = new ArrayList<Channels>();
        diamond_channel_list = new ArrayList<Channels>();

        crystal_channelTitle.add("ABS-CBN");
        crystal_channelTitle.add("PTV");
        crystal_channelTitle.add("TV5");
        crystal_channelTitle.add("COMMUNITY CHANNEL");
        crystal_channelTitle.add("GMA 7");
        crystal_channelTitle.add("CINEMA ONE");
        crystal_channelTitle.add("ISLAND LIVING CHANNEL");
        crystal_channelTitle.add("JEEPNEY TV");
        crystal_channelTitle.add("GNN");
        crystal_channelTitle.add("IBC 13");
        crystal_channelTitle.add("PAY-PER-VIEW");
        crystal_channelTitle.add("HOPE INTERNATIONAL");
        crystal_channelTitle.add("CNN PHILIPPINES");
        crystal_channelTitle.add("GMA NEWS TV");
        crystal_channelTitle.add("SPORTS + ACTION");
        crystal_channelTitle.add("DZMM TELERADIO");
        crystal_channelTitle.add("DZRH NEWS");
        crystal_channelTitle.add("KNOWLEDGE CHANNEL");
        crystal_channelTitle.add("LIVING ASIA");
        crystal_channelTitle.add("DIVA");
        crystal_channelTitle.add("HBO");
        crystal_channelTitle.add("FOX MOVIES");
        crystal_channelTitle.add("CINEMAX");
        crystal_channelTitle.add("LOTUS MACAU");
        crystal_channelTitle.add("FOX FAMILY MOVIES");
        crystal_channelTitle.add("AKSYON TV");
        crystal_channelTitle.add("FOX LIFE");
        crystal_channelTitle.add("WARNER TV");
        crystal_channelTitle.add("CARTOON NETWORK");
        crystal_channelTitle.add("YEYI");
        crystal_channelTitle.add("JACK TV");
        crystal_channelTitle.add("ANIMAX");
        crystal_channelTitle.add("DISNEY CHANNEL");
        crystal_channelTitle.add("NICKELODEON");
        crystal_channelTitle.add("SOLAR SPORTS");
        crystal_channelTitle.add("FOX SPORTS");
        crystal_channelTitle.add("FOX SPORTS 2");
        crystal_channelTitle.add("BTV");
        crystal_channelTitle.add("TRT WORLD");
        crystal_channelTitle.add("AKLASS  TWO");
        crystal_channelTitle.add("DUBAI SPORTS");
        crystal_channelTitle.add("I24 NEWS");
        crystal_channelTitle.add("CNN INTERNATIONAL");
        crystal_channelTitle.add("BBC");
        crystal_channelTitle.add("CHANNEL NEWS ASIA");
        crystal_channelTitle.add("BLOOMBERG");
        crystal_channelTitle.add("AL JAZEERA INT'L");
        crystal_channelTitle.add("ANC");
        crystal_channelTitle.add("CGTN");


        diamond_channelTitle.add("TVN");
        diamond_channelTitle.add("FOX CHANNEL");
        diamond_channelTitle.add("FOX NEWS");
        diamond_channelTitle.add("FOX CRIME");
        diamond_channelTitle.add("FX");
        diamond_channelTitle.add("ANIMAL PLANET");
        diamond_channelTitle.add("DISCOVERY WORLD");
        diamond_channelTitle.add("SETANTA SPORTS");
        diamond_channelTitle.add("SONY CHANNEL");
        diamond_channelTitle.add("DISNEY JUNIOR");
        diamond_channelTitle.add("HISTORY");
        diamond_channelTitle.add("FYI,");
        diamond_channelTitle.add("TRU TV");
        diamond_channelTitle.add("AMC");
        diamond_channelTitle.add("E!");
        diamond_channelTitle.add("OUTDOOR CHANNEL");
        diamond_channelTitle.add("NBA PREMIUM TV");

        for (int i = 0; i < crystal_channelTitle.size(); i++){
            Channels channels = new Channels(crystal_channelTitle.get(i));
            crystal_channel_list.add(channels);
        }
        for (int i = 0; i < diamond_channelTitle.size(); i++){
            Channels channels = new Channels(diamond_channelTitle.get(i));
            diamond_channel_list.add(channels);
        }
        if(packages.equals("Crystal Package")){
            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                recyclerView = (RecyclerView) view;
                recyclerView.setAdapter(new ChannelRecyclerView(crystal_channel_list, mListener));
            }
        }else {
            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                recyclerView = (RecyclerView) view;
                recyclerView.setAdapter(new ChannelRecyclerView(diamond_channel_list, mListener));
            }
        }


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
