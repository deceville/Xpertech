package com.company.xpertech.xpertech.Nav_Fragment.Remote_Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.company.xpertech.xpertech.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RemoteListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RemoteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemoteListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private ListView listView;
     Context ctx;

    private OnFragmentInteractionListener mListener;

    public RemoteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemoteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoteListFragment newInstance(String param1, String param2) {
        RemoteListFragment fragment = new RemoteListFragment();
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

        getActivity().setTitle("Remote");
        view = inflater.inflate(R.layout.fragment_remote_list, container, false);

        String inst[] = {"1. Install the batteries.", "2. Find the manufacturer’s code for your TV (see other side).", "3. Turn on your TV. ",
                "4. Press and hold the SET button until the red light flashes twice, then release.", "5. Enter the first manufacturer’s code you found in step 2. The red light will flash twice. ",
                "6. Press the POWER button. If the TV turns off, make sure that the VOL+/- and MUTE buttons also work. If so, you are all set!",
                "7. If not, repeat the steps using another manufacturer’s code. ", "8. If it still doesn’t work using all the codes you can find, contact Customer Support. "};

        listView = (ListView) view.findViewById(R.id.remote_list);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                inst
        );

        final FragmentActivity ft = (FragmentActivity) ctx;

        listView.setAdapter(listViewAdapter);

        Button btn_remote = (Button) view.findViewById(R.id.btn_remote);
        btn_remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new RemoteItemFragment()).addToBackStack("remote").commit();
            }
        });
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

        ctx = context;
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
