package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    EditText editPhoneNo;
    Button callbtn;
    String phoneNoStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Call");

        initView();

        callbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                phoneNoStr = editPhoneNo.getText().toString();

                if (!phoneNoStr.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+phoneNoStr));
                    startActivity(intent);
                }else {
                    Toast.makeText(CallActivity.this, "Please fill in the Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initView() {
        editPhoneNo = findViewById(R.id.editPhoneNo);
        callbtn = findViewById(R.id.callbtn);
    }
}