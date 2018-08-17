package com.company.xpertech.xpertech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entercode);

        //Cancel Button
        Button btn_enter_cancel = (Button) findViewById(R.id.btn_enter_cancel);

        btn_enter_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterCodeActivity.this, SignUpActivity.class));
            }
        });

        //Enter Button
        Button btn_enter_accNum = (Button) findViewById(R.id.btn_enter_accNum);

        btn_enter_accNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterCodeActivity.this, DeviceSummaryActivity.class));
            }
        });


    }
}
