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
import android.util.Log;
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

import pl.droidsonroids.gif.GifImageView;

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
    static ArrayList<Troubleshoot> troubleshootArrayList= new ArrayList<Troubleshoot>();
    int cnt = 0;
    Context ctx;
    View view;
    GifImageView gif;

    String BOX_NUMBER_SESSION;

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

        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        BOX_NUMBER_SESSION = bundle.getString("BOX_NUMBER_SESSION");
        troubleshootArrayList= new ArrayList<Troubleshoot>();
        SubMenuTask subMenuTask = new SubMenuTask(getContext());
        subMenuTask.execute("troubleshoot_steps", (position+1)+"");
        Log.d("SIZE", troubleshootArrayList.size()+"");
    }

    public void next(final int index){
        TextView txt = (TextView) this.view.findViewById(R.id.item_text);
        Troubleshoot troubleshoot = troubleshootArrayList.get(index);
        txt.setText(troubleshoot.getInstruct());
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        final FragmentActivity actvty = (FragmentActivity) ctx;
        this.view = view;

        gif = (GifImageView) view.findViewById(R.id.gif_imageView);


        Button btn_done = (Button) view.findViewById(R.id.btn_done);

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
                            next(cnt);
                        } else {
                            troubleshootArrayList = new ArrayList<Troubleshoot>();
                            Bundle bundle = new Bundle();
                            bundle.putString("BOX_NUMBER_SESSION", BOX_NUMBER_SESSION);
                            TroubleshootFragment tf = new TroubleshootFragment();
                            tf.setArguments(bundle);
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
                                troubleshootArrayList = new ArrayList<Troubleshoot>();
                                d.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putString("BOX_NUMBER_SESSION", BOX_NUMBER_SESSION);
                                TroubleshootFragment tf = new TroubleshootFragment();
                                tf.setArguments(bundle);
                                actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                            }
                        });
                        btn_call.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                                troubleshootArrayList = new ArrayList<Troubleshoot>();
                                Bundle bundle = new Bundle();
                                bundle.putString("BOX_NUMBER_SESSION", BOX_NUMBER_SESSION);
                                TroubleshootFragment tf = new TroubleshootFragment();
                                tf.setArguments(bundle);
                                actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_troubleshoot_item, container, false);
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

    public class SubMenuTask extends AsyncTask<String,Void,String> {
        Context ctx;
        AlertDialog alertDialog;

        public SubMenuTask(Context ctx)
        {
            this.ctx =ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("");
        }
        @Override
        protected String doInBackground(String... params) {
            String troubleshoot_url = "http://10.0.2.2/xpertech/troubleshoot_steps.php";
            String method = params[0];
            if(method.equals("troubleshoot_steps")){
                String troubleshoot_id = params[1];
                try {
                    URL url = new URL(troubleshoot_url);
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
                    line = bufferedReader.readLine();

                    String[] step = line.split("\\$");
                    for (int i = 0; i < step.length; i++) {
                        TroubleeshootItemFragment.troubleshootArrayList.add(new Troubleshoot(step[i],0));
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
            if (position == 2){
                gif.setImageResource(R.drawable.remote);
            }else if (position == 3){
                gif.setImageResource(R.drawable.greenled);
            }else if (position == 4){
                gif.setImageResource(R.drawable.audiovideo);
            }else if (position == 5){
                gif.setImageResource(R.drawable.coxial);
            }
            next(cnt);
        }


    }
}
