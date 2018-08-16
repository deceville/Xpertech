package com.company.xpertech.xpertech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class TroubleshootActivity extends AppCompatActivity {

    ListView list;
    ListViewAdapter adapter;
    ArrayList <String> troubleshootTitle;
    ArrayList <Troubleshoot> troubleshootList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troubleshoot);

        troubleshootTitle = new ArrayList<String>();
        troubleshootList = new ArrayList<Troubleshoot>();

        // list of titles
        troubleshootTitle.add("Set Top Box (STB) Configuration");
        troubleshootTitle.add("TV Configuration (via Simple Set Button)");
        troubleshootTitle.add("My set top box (STB) is not Booting Up");
        troubleshootTitle.add("My TV has No Audio and/or Video Output");
        troubleshootTitle.add("My TV is Showing \"Technical Problem\" Error/Pixilated Pictures/ON and OFF Programming");
        troubleshootTitle.add("My TV Screen is Showing an Error Code - E1 / E2 / E11 / E4 / E6 / E14");

        list = (ListView) findViewById(R.id.listview_troubleshoot);

        for (int i = 0; i < troubleshootTitle.size(); i++){
            Troubleshoot trbl = new Troubleshoot(troubleshootTitle.get(i));
            troubleshootList.add(trbl);
        }

        adapter = new ListViewAdapter(getApplicationContext(), troubleshootList);

        list.setAdapter(adapter);


    }
}
