package com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.company.xpertech.xpertech.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TroubleeshootItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TroubleeshootItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TroubleeshootItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static String data = null;
    private static int position = 0;
    ArrayList<Troubleshoot> troubleshootArrayList= new ArrayList<Troubleshoot>();
    int cnt = 0;
    Context ctx;

    private OnFragmentInteractionListener mListener;

    public TroubleeshootItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TroubleeshootItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TroubleeshootItemFragment newInstance(String param1, String param2) {
        TroubleeshootItemFragment fragment = new TroubleeshootItemFragment();
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

    public void next(final int index, View view){
        TextView txt = (TextView) view.findViewById(R.id.item_text);
        Troubleshoot troubleshoot = troubleshootArrayList.get(index);
        txt.setText(troubleshoot.getInstruct());
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        data = bundle.getString("data");
        position = bundle.getInt("position");
        getProcess(position);
        final FragmentActivity actvty = (FragmentActivity) ctx;

        Button btn_done = (Button) view.findViewById(R.id.btn_done);
        next(cnt, view);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(getContext());
                d.setContentView(R.layout.fragment_troubleshoot_dialog);
                d.show();
                final TextView dialog_text = (TextView) d.findViewById(R.id.dialog_text);
                dialog_text.setText("Were you able to perform the process?");
                if (cnt == troubleshootArrayList.size()-1)
                    dialog_text.setText("Was the problem fixed?");

                final Button btn_back = (Button) d.findViewById(R.id.btn_back);
                btn_back.setText("Yes");
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                        cnt++;
                        if(cnt < troubleshootArrayList.size()){
                            next(cnt, view);
                        } else {
                            TroubleshootFragment tf = new TroubleshootFragment();
                            actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                        }
                    }
                });

                final Button btn_call = (Button) d.findViewById(R.id.btn_call);
                btn_call.setText("No");
                btn_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                        dialog_text.setText("Would you like to call customer service now?");
                        d.show();
                        btn_back.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:4458514"));

                                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    startActivity(callIntent);
                                } else {
                                    requestPermissions(new String[]{CALL_PHONE}, 1);
                                }

                                d.dismiss();
                                TroubleshootFragment tf = new TroubleshootFragment();
                                actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                            }
                        });
                        btn_call.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                                TroubleshootFragment tf = new TroubleshootFragment();
                                actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                            }
                        });
                    }
                });
            }
        });

    }

    private void getProcess(int position){
        switch(position){
            case 0:
                troubleshootArrayList.add(new Troubleshoot("1.)\tOn the Remote, press and hold OK button and Power button simultaneously until LED blinks 2x", 0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tPress 9-8-2 on the remote to unlock it for RCU programming, LED should blink 4x", 0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tPress and hold the OK button and Power button again simultaneously for 3-5 seconds until the LED blinks 2x", 0));
                troubleshootArrayList.add(new Troubleshoot("4.)\tPress the assigned code 4998/2319 to be controlled", 0));
                troubleshootArrayList.add(new Troubleshoot("5.)\tRemote control LED will blink 2x once correct code is entered", 0));
                troubleshootArrayList.add(new Troubleshoot("6.)\tPress and hold the OK button and Power button simultaneously for 3-5 seconds until the LED blinks 2x", 0));
                troubleshootArrayList.add(new Troubleshoot("7.)\tPress 9-8-2 on the remote to lock it for RCU programming, LED should blink 2x", 0));
                break;
//                GifImageView gif = (GifImageView) tru_view.findViewById(R.id.troubleshoot_item_image);
//                gif.setImageResource(R.drawable.greenled);
            case 1:
                troubleshootArrayList.add(new Troubleshoot("1.)\tTurn on your TV",0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tPress and hold the Simple Set button until LED blinks 2x",0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tWhile pointing the remote control to your TV, press and hold the number button which corresponds to your TV brand until the TV turns off by itself\n" +
                        "        1 = Samsung\t6 = Sharp\n" +
                        "        2 = LG\t7 = Philips\n" +
                        "        3 = Sony\t8 = JVC\n" +
                        "        4 = Panasonic\t9 = Hitachi\n" +
                        "        5 = Toshiba\t0 = Haier\n", 0));
                troubleshootArrayList.add(new Troubleshoot("4.)\tTurn on your TV using the TV remote control",  0));
                troubleshootArrayList.add(new Troubleshoot("5.)\tOnce turned on, perform a test using your remote control to your TV by", 0));
                troubleshootArrayList.add(new Troubleshoot("6.)\tpressing the Volume Up and Volume Down", 0));
                troubleshootArrayList.add(new Troubleshoot("7.)\tpressing the Mute button", 0));
                troubleshootArrayList.add(new Troubleshoot("8.)\tTest the Channel +/- key on the Remote control", 0));
                break;
            case 2:
                troubleshootArrayList.add(new Troubleshoot("1.)\tMake sure your STB is plugged in", 0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tCheck your STB front panel if it is turned on (LED is green)", 0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tIf light is green and still not booting up, perform hard reset by unplugging the STB from the wall socket and plug it back in after 5 seconds", 0));
                break;
            case 3:
                troubleshootArrayList.add(new Troubleshoot("1.)\tMake sure that your TV is not on standby mode", 0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tCheck the connections between the STB and TV if firmly and properly connected", 0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tOn your TV, select the correct Audio/Video input or source (example: AV1, AV2, HDM1 HDM2, etc.)", 0));
                troubleshootArrayList.add(new Troubleshoot("4.)\tPower on the l STB", 0));
                troubleshootArrayList.add(new Troubleshoot("5.)\tCheck TV and STB volume functions", 0));
                troubleshootArrayList.add(new Troubleshoot("6.)\tIf issue persists, perform hard reset by unplugging the STB from the wall socket and plug it back in after 5 seconds", 0));
                break;
            case 4:
                troubleshootArrayList.add(new Troubleshoot("1.)\tCheck if coaxial cable (RG6) is firmly connected and secured", 0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tPress the MENU button on your remote control then navigate to SETTINGS", 0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tKey in default PIN as 0000 or 9998", 0));
                troubleshootArrayList.add(new Troubleshoot("4.)\tNavigate to the following options SYSTEM SETUP > INSTALLATION SETUP > SATELLITE SETUP > LNB POWERING", 0));
                troubleshootArrayList.add(new Troubleshoot("5.)\tToggle ON/OFF using the LEFT and RIGHT buttons on the remote", 0));
                troubleshootArrayList.add(new Troubleshoot("6.)\tfor the Primary STB - must be set to ON", 0));
                troubleshootArrayList.add(new Troubleshoot("7.)\tfor 2nd/3rd STB - must be set to OFF",0));
                break;
            case 5:
                troubleshootArrayList.add(new Troubleshoot("1.)\tTurn the STB off and locate where the smart card is inserted", 0));
                troubleshootArrayList.add(new Troubleshoot("2.)\tGently take out the smart card and check for any physical defects", 0));
                troubleshootArrayList.add(new Troubleshoot("3.)\tYou may try to wipe the gold chip with a soft, dry, non-abrasive cloth to clear any dirt build up", 0));
                troubleshootArrayList.add(new Troubleshoot("4.)\tInsert the smart card back to the card slot the same way how it was removed", 0));
                troubleshootArrayList.add(new Troubleshoot("5.)\tMake sure that the smart card is properly inserted and seated securely", 0));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_troubleshoot_item, container, false);
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

    public class SubMenuTask extends AsyncTask<String,Void,String> {
        AlertDialog alertDialog;
        Context ctx;
        SubMenuTask(Context ctx)
        {
            this.ctx =ctx;
        }
        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information....");
        }
        @Override
        protected String doInBackground(String... params) {
            String troubleshoot_steps_url = "http://10.0.2.2/xpertech/troubleshoot_steps.php";
            String selfinstall_steps_url = "http://10.0.2.2/xpertech/selfinstall_steps.php";
            String usermanual_steps_url = "http://10.0.2.2/xpertech/usermanual_steps.php";
            String method = params[0];
            if(method.equals("troubleshoot")){
                String troubleshoot_id = params[1];
                try {
                    URL url = new URL(troubleshoot_steps_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("troubleshoot_id","UTF-8")+"="+URLEncoder.encode(troubleshoot_id,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(method.equals("selfinstall")){
                String selfinstall_id = params[1];
                try {
                    URL url = new URL(selfinstall_steps_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("selfinstall_id","UTF-8")+"="+URLEncoder.encode(selfinstall_id,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(method.equals("usermanual")){
                String manual_id = params[1];
                try {
                    URL url = new URL(usermanual_steps_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("manual_id","UTF-8")+"="+URLEncoder.encode(manual_id,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result) {
            String [] step = result.split("\\$");
            ArrayList<String> step_list = new ArrayList<String>();
            for (int i = 0; i < step.length; i++){
                step_list.add(step[i]);
            }
            alertDialog.setMessage(step_list.get(0));
            alertDialog.show();

        }
    }
}
