package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScannedListActivity extends AppCompatActivity {

    //    TextView textView;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_list);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Scanned List");

//        textView = findViewById(R.id.tv_scanTextView);
//        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("scannedList");
//        textView.setText(String.valueOf(list));

        listView2 = findViewById(R.id.scanned_listview);
        ArrayList<String> list2 = (ArrayList<String>) getIntent().getSerializableExtra("scannedList");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScannedListActivity.this, android.R.layout.simple_list_item_1,list2);
        listView2.setAdapter(adapter);

        listView2.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ScannedListActivity.this, list2.get(position)+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScannedListActivity.this, ParcelDetailsActivity.class);
                String trackingNumber =  list2.get(position);
                intent.putExtra("SELECTED_TRACKING_NUM", trackingNumber);
                startActivity(intent);
            }
        }));
    }
}