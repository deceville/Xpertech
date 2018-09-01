package com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.company.xpertech.xpertech.Method.Task;
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
    String USER_SESSION;

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
        position = bundle.getInt("position")+1;

        SharedPreferences s = this.getActivity().getSharedPreferences("values", Context.MODE_PRIVATE);
        BOX_NUMBER_SESSION = s.getString("BOX_NUMBER_SESSION", "BOX_NUMBER_SESSION");
        BOX_NUMBER_SESSION = BOX_NUMBER_SESSION.replaceAll("\\s+","");
        USER_SESSION = s.getString("USER_SESSION", "USER_SESSION");

        troubleshootArrayList= new ArrayList<Troubleshoot>();
        SubMenuTask subMenuTask = new SubMenuTask(getContext());
        subMenuTask.execute("stat","troubleshoot_steps", position+"",BOX_NUMBER_SESSION);
        Log.d("SIZE", troubleshootArrayList.size()+"");
    }

    public void next(final int index){
        TextView txt = (TextView) this.view.findViewById(R.id.item_text);
        Troubleshoot troubleshoot = troubleshootArrayList.get(index);
        int img = 0;
        if (position == 2){
            img = R.drawable.remote;
        }else if (position == 3){
            img = R.drawable.greenled;
        }else if (position == 4){
            img = (R.drawable.audiovideo);
        }else if (position == 5){
            img = (R.drawable.coxial);
        }
        Log.d("IMG",""+img);
        gif.setImageResource(img);
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
                            TroubleshootFragment tf = new TroubleshootFragment();
                            Task task = new Task();
                            task.execute("stat", "troubleshoot", "pass", USER_SESSION);
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
                        Task task = new Task();
                        task.execute("stat", "troubleshoot", "fail", USER_SESSION);
                        dialog_text.setText("Would you like to call customer service now?");
                        d.show();
                        btn_back.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:4458514"));

                                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    Task task = new Task();
                                    task.execute("stat","call", "pass", USER_SESSION);
                                    startActivity(callIntent);
                                } else {
                                    requestPermissions(new String[]{CALL_PHONE}, 1);
                                }
                                troubleshootArrayList = new ArrayList<Troubleshoot>();
                                d.dismiss();
                                TroubleshootFragment tf = new TroubleshootFragment();
                                actvty.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, tf).commit();
                            }
                        });
                        btn_call.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                                Task task = new Task();
                                task.execute("stat","call", "fail", USER_SESSION);
                                troubleshootArrayList = new ArrayList<Troubleshoot>();
                                TroubleshootFragment tf = new TroubleshootFragment();
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
            String install_url = "https://uslsxpertech.000webhostapp.com/xpertech/troubleshoot_steps.php";
            String title_url = "https://uslsxpertech.000webhostapp.com/xpertech/selfinstall_title.php";
            String img_url = "https://uslsxpertech.000webhostapp.com/xpertech/selfinstall_image.php";
            String method = params[0];
            if(method.equals("troubleshoot_steps")){
                String troubleshoot_id = params[1];
                String box_id = params[2];
                try {
                    //Retreiving Steps
                    URL url = new URL(install_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("troubleshoot_id","UTF-8")+"="+URLEncoder.encode(troubleshoot_id,"UTF-8");
                    data += "&" + URLEncoder.encode("box_id","UTF-8")+"="+URLEncoder.encode(box_id,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String line = "";
                    line = bufferedReader.readLine();

                    String[] step = line.split("\\$");
                    for (int i = 0; i < step.length; i++) {
                        TroubleeshootItemFragment.troubleshootArrayList.add(new Troubleshoot(step[i],0));
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
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
            next(cnt);
        }
    }
}
