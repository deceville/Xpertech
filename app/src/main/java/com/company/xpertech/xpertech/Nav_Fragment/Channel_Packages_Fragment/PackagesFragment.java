package com.company.xpertech.xpertech.Nav_Fragment.Channel_Packages_Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.xpertech.xpertech.Method.Packages;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackagesFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    ArrayList <String> packageTitle;
    ArrayList <String> packageChannel;
    ArrayList<Packages> packagesList;

    static String BOX_NUMBER_SESSION;
    static Bundle BUNDLE_SESSION;
    RecyclerView recyclerView;

    public PackagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PackagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackagesFragment newInstance(int columnCount) {
        PackagesFragment fragment = new PackagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences s = this.getActivity().getSharedPreferences("values", Context.MODE_PRIVATE);
        //s = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        BOX_NUMBER_SESSION = s.getString("BOX_NUMBER_SESSION", "BOX_NUMBER_SESSION");
        BUNDLE_SESSION = getArguments();
        //BOX_NUMBER_SESSION = BUNDLE_SESSION.getString("BOX_NUMBER_SESSION");
        String method = "package";

        Log.d("BOX_NUMBER_SESSION",BOX_NUMBER_SESSION);
        MenuTask menuTask = new MenuTask(getContext());
        menuTask.execute(method, BOX_NUMBER_SESSION);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Packages and Channel Lineup");

        final View view = inflater.inflate(R.layout.fragment_packages_list, container, false);
        packageTitle = new ArrayList<String>();
        packageChannel = new ArrayList<String>();
        packagesList = new ArrayList<Packages>();


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnListFragmentInteractionListener(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Packages item);
    }

    public class MenuTask extends AsyncTask<String,Void,String> {
        Context ctx;
        AlertDialog alertDialog;

        public MenuTask(Context ctx)
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
            String packages_url = "http://10.0.2.2/xpertech/packages.php";
            String method = params[0];
            if(method.equals("package")){
                String box_number = params[1];
                try {
                    URL url = new URL(packages_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("box_number","UTF-8")+"="+URLEncoder.encode(box_number,"UTF-8");
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
            String[] title = result.split("\\$");
            for (int i = 0; i < title.length; i++) {
                packageTitle.add(title[i]);
            }
            /*String [] items = new String[2];
            for (int i = 0; i < title.length; i++) {
                items = title[i].split("\\,");
            }

            packageTitle.add(items[0]);
            packageChannel.add(items[1]);

            Log.d("packageTitle",items[0]);
            Log.d("packageChannel",items[1]);*/
            for (int i = 0; i < packageTitle.size(); i++){
                Packages packages = new Packages(packageTitle.get(i));
                packagesList.add(packages);
            }
            recyclerView.setAdapter(new PackagesRecyclerView(packagesList, mListener));
        }
        /*Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("title_list", title_list);
        msg.setData(bundle);
        Handler handler = new Handler();
        handler.sendMessage(msg);*/


    }

}
