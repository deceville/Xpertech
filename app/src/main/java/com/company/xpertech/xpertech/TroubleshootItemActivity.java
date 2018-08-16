package com.company.xpertech.xpertech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TroubleshootItemActivity extends AppCompatActivity {

    ListView list;
    ListViewItemAdapter adapter;
    ArrayList<String> troubleshootItem;
    ArrayList <TroubleshootItem> troubleshootItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troubleshoot_item);

        troubleshootItem = new ArrayList<String>();
        troubleshootItemList = new ArrayList<TroubleshootItem>();


//        troubleshootItem.add("On the Remote, press and hold OK button and Power button simultaneously until LED blinks 2x");
//        troubleshootItem.add("Press 9-8-2 on the remote to unlock it for RCU programming, LED should blink 4x");
//        troubleshootItem.add("Press and hold the OK button and Power button again simultaneously for 3-5 seconds until the LED blinks 2x");
//        troubleshootItem.add("Press the assigned code 4998/2319 to be controlled");
//        troubleshootItem.add("Remote control LED will blink 2x once correct code is entered");
//        troubleshootItem.add("Press and hold the OK button and Power button simultaneously for 3-5 seconds until the LED blinks 2x");
//        troubleshootItem.add("Press 9-8-2 on the remote to lock it for RCU programming, LED should blink 2x");

        troubleshootItem.add("Make sure your STB is plugged in");
        troubleshootItem.add("Check your STB front panel if it is turned on (LED is green)");
        troubleshootItem.add("If light is green and still not booting up, perform hard reset by unplugging the STB from the wall socket and plug it back in after 5 seconds");
        list = (ListView) findViewById(R.id.listview_troubleshootItem);

        for (int i = 0; i < troubleshootItem.size(); i++){
            TroubleshootItem trbl_item = new TroubleshootItem(troubleshootItem.get(i));
            troubleshootItemList.add(trbl_item);
        }

        adapter = new ListViewItemAdapter(getApplicationContext(), troubleshootItemList);

        TextView instruction = (TextView) findViewById(R.id.troubleshoot_item);

        String tempText = "";
        for(int i = 0; i < 7; i++)
        {
            tempText += "(" + (i+1) + ") " + troubleshootItem.get(i) + "\n";
        }
        instruction.setText(tempText);

//        list.setAdapter(adapter);


    }
}
