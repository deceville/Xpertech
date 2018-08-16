package com.company.xpertech.xpertech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeviceSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_summary);

        //Back Buutton
        Button btn_back = (Button) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceSummaryActivity.this, SignUpActivity.class));
            }
        });

        //Proceed utton
        Button btn_proceed = (Button) findViewById(R.id.btn_proceed);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceSummaryActivity.this, MainActivity.class));
            }
        });
    }
}
