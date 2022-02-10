package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;


public class SendWhatsappMsgActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phone, message;
    Button sendbtn;
    String phonestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_whatsapp_msg);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Send Message");

        countryCodePicker = findViewById(R.id.countryCode);
        phone = findViewById(R.id.phoneNo);
//        message = findViewById(R.id.message);
        sendbtn = findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                messagestr = message.getText().toString();
                phonestr = phone.getText().toString();

                if (!phonestr.isEmpty()) {

                    countryCodePicker.registerCarrierNumberEditText(phone);
                    phonestr = countryCodePicker.getFullNumber();

                    if (isWhatappInstalled()){

                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phonestr+
                                "&text= Hi, GDEX dispatcher here."));
                        startActivity(i);
//                        message.setText("");
                        phone.setText("");


                    }else {

                        Toast.makeText(SendWhatsappMsgActivity.this,"Whatsapp is not installed",Toast.LENGTH_SHORT).show();

                    }


                } else {

                    Toast.makeText(SendWhatsappMsgActivity.this, "Please fill in the Phone number", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private boolean isWhatappInstalled(){

        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;

        try {

            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            whatsappInstalled = true;


        }catch (PackageManager.NameNotFoundException e){

            whatsappInstalled = false;

        }

        return whatsappInstalled;

    }


}