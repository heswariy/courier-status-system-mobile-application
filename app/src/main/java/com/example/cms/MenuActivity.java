package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    ImageButton btnPickupScan, btnDeliveryScan, btnList, btnCompletedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.hide();

        initView();

        btnPickupScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        btnDeliveryScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ScannedListActivity.class);
                startActivity(intent);
            }
        });

        btnCompletedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CompletedActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initView() {
        btnPickupScan = findViewById(R.id.btnPickupScan);
        btnDeliveryScan = findViewById(R.id.btnDeliveryScan);
        btnList = findViewById(R.id.btnList);
        btnCompletedList = findViewById(R.id.btnCompletedList);
    }
}