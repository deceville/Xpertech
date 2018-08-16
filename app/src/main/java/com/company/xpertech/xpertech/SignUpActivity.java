package com.company.xpertech.xpertech;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity{

    Button btn_entercode;
    SurfaceView cameraPreview;

    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    TextView qr_details;

    final int RequestCameraPermissionID = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show EULA

        new AppEULA(this).show();

        setContentView(R.layout.activity_scan_qr);

        //Open Enter Code Activity
        btn_entercode = (Button) findViewById(R.id.btn_entercode);

        btn_entercode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, EnterCodeActivity.class));
            }
        });

        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
        //txtResult = (TextView) findViewById(R.id.txtResult);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        //Add Event

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {


            }


            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() != 0) {
                    String result = qrcodes.valueAt(0).displayValue;

                    dialogHandler(result);
                }//else {
                 //   startActivity(new Intent(SignUpActivity.this, DeviceSummaryActivity.class));
                //}
            }
        });

    }

    private void dialogHandler(String result) {

        final String shareResult = result;

        final Dialog d = new Dialog(getApplicationContext());
        d.setTitle("Cable Box Details");
        d.setContentView(R.layout.activity_device_summary);
        d.setCancelable(false);

        qr_details = (TextView) d.findViewById(R.id.qr_details);

        qr_details.setText(result);

        //Back Button
        Button btn_back = (Button) d.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        //Proceed button
        Button btn_proceed = (Button) d.findViewById(R.id.btn_proceed);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        d.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

}
