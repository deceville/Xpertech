package com.company.xpertech.xpertech.Nav_Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.xpertech.xpertech.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Sub_Manual_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Sub_Manual_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sub_Manual_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int position = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Sub_Manual_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sub_Manual_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Sub_Manual_Fragment newInstance(String param1, String param2) {
        Sub_Manual_Fragment fragment = new Sub_Manual_Fragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        TextView txtTitle = (TextView) view.findViewById(R.id.manual_title);
        TextView txtContent = (TextView) view.findViewById((R.id.manual_content));

        switch(position){
            case 0:
                txtTitle.setText("Unpacking");
                txtContent.setText(
                        "\tYour service package selected determines the range of capabilities of your cable box. " +
                                "But for every service, the self-install kit should be inclusive of the following:\n" +
                                "1.) Set Top Box (STB)\n" +
                                "2.) Infrared remote control \n" +
                                "3.) Battery\n" +
                                "4.) Power Adapter\n" +
                                "5.) Audio or Video Cable\n" +
                                "6.) Video Graphics Array (VGA) Cable\n" +
                                "7.) Coax Cable\n" +
                                "8.) User Guide"
                );
                break;
            case 1:
                txtTitle.setText("Plugging In");
                txtContent.setText(
                        "\tThis Self-install guide are for those who wanted to set the installation on their own, " +
                                "assuming that the subscribers' outside connections are all set. \n" +
                                "1.) Screw Coax Cable from wall outlet to the back of the cable box. \n" +
                                "2.) If box is HD capable, connect the VGA cable from the VGA slot at the back of the box to the respective VGA outlet on your TV. " +
                                "If the box is 'not' HD capable, connect the other coaxial cable from the cable box to your TV.\n" +
                                "3.) Connect the power cable into the back of the Set Top Box to the power outlet."
                );
                break;
            case 2:
                txtTitle.setText("Powering up the box ");
                txtContent.setText(
                        "1.)  When all connections said are all set, turn ON the power on your cable box by pressing the Power Button " +
                                "in the front end of your box or Press the Power Button on your cable box Remote Control \n" +
                                "2.) If you connected your cable box to your TV with coaxial cables, Turn your TV Channel to Channel 2 to see if you got picture. " +
                                "If not, turn your TV to Channels 3 or 4 until you got picture. " +
                                "If you connected HDMI cable from the box to your TV, check the Channel sourcing of your TV and make sure it is tuned in to " +
                                "the appropriate HDMI mode where you connected your HDMI cable at. Either HDMI 1 or HDMI 2, etc\n"
                );
                break;
            case 3:
                txtTitle.setText("Support and Activating Service");
                txtContent.setText(
                        "1.)  If you are able to see pictures already, congratulations! You have successfully set your cable service up! If you are still having issues, visit our website at \n" +
                                "www.newbacolodcabletv.com or call us at (034) 445-8514 or message us directly on Facebook at New Bacolod CableTV.\n" +
                                "2.) If your service is not yet activated, call our direct hotline at (034) 445-8514."
                );
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub__manual_, container, false);
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