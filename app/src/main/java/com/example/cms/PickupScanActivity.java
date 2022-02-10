package com.example.cms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacterDirection;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PickupScanActivity extends AppCompatActivity {

    Button btn_Upload, btn_Cancel;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_scan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Pick-Up Scan List");

//        textView = findViewById(R.id.textView);
//        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("trackingList");
//        textView.setText(String.valueOf(list));

        btn_Upload = findViewById(R.id.btn_Upload);
        btn_Cancel = findViewById(R.id.btn_Cancel);

        listView = findViewById(R.id.parcel_listview);
        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("trackingList");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickupScanActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        btn_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPref = getSharedPreferences("scannedList", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putStringSet("list", list);
//                editor.apply();
                Intent intent = new Intent(PickupScanActivity.this, ScannedListActivity.class);
                startActivity(intent);
                Toast.makeText(PickupScanActivity.this, "Parcel list has been uploaded", Toast.LENGTH_SHORT).show();
                intent.putExtra("scannedList", list);
                startActivity(intent);
                // list.clear();
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

