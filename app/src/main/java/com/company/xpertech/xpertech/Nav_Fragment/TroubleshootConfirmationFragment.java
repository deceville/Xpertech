 package com.company.xpertech.xpertech.Nav_Fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.company.xpertech.xpertech.R;

 /**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TroubleshootConfirmationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TroubleshootConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TroubleshootConfirmationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentActivity activity;

    private OnFragmentInteractionListener mListener;

    public TroubleshootConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_fixed = (Button) view.findViewById(R.id.btn_fixed);
        Button btn_notfixed = (Button) view.findViewById(R.id.btn_notfixed);

        btn_fixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(getContext());
                d.setContentView(R.layout.fragment_troubleshoot_dialog);
                d.show();
                TextView dialog_text = (TextView) d.findViewById(R.id.dialog_text);
                dialog_text.setText("Good job! You have solved the problem.");

                Button btn_back = (Button) d.findViewById(R.id.btn_back);
                btn_back.setText("Back to Main Menu");
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment()).addToBackStack("tag").commit();
                        d.dismiss();
                    }
                });
            }
        });

        btn_notfixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(getContext());
                d.setContentView(R.layout.fragment_troubleshoot_dialog);

                TextView dialog_text = (TextView) d.findViewById(R.id.dialog_text);
                dialog_text.setText("Sorry. You can try another one.");

                Button btn_back = (Button) d.findViewById(R.id.btn_back);
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TroubleshootFragment()).commit();
                        d.dismiss();
                    }
                });
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TroubleshootConfirmationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TroubleshootConfirmationFragment newInstance(String param1, String param2) {
        TroubleshootConfirmationFragment fragment = new TroubleshootConfirmationFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_troubleshoot_confirmation, container, false);
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
        activity = (FragmentActivity) context;
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
