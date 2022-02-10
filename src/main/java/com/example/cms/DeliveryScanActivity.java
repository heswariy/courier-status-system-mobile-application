package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class DeliveryScanActivity extends AppCompatActivity {

    EditText et_dataInput;
    ImageButton btnQrScanner;
    ListView lv_ScannedList;
    Button btn_Upload, btn_Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_scan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Delivery Scan List");

        initView();

        btnQrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryScanActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryScanActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    public void initView() {
        et_dataInput = findViewById(R.id.et_dataInput);
        btnQrScanner = findViewById(R.id.btnQrScanner);
        lv_ScannedList = findViewById(R.id.lv_ScannedList);
        btn_Upload = findViewById(R.id.btn_Upload);
        btn_Cancel = findViewById(R.id.btn_Cancel);
    }

}