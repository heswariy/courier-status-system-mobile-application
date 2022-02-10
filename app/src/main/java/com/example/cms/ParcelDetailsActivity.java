package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParcelDetailsActivity extends AppCompatActivity {

    TextView trackNumFromDB, statusFromDB, senderNameFromDB, senderContactFromDB, senderAltContactFromDB;
    TextView receiverNameFromDB, receiverContactFromDB, receiverAltContactFromDB, receiverAddressFromDB;
    ImageButton btnCall, btnMessage, btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_details);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Parcel Details");

        initView();

        String result = (String) getIntent().getStringExtra("SELECTED_TRACKING_NUM");
        String url ="http://heswariy-001-site1.dtempurl.com/api/Parcel/" + result;

        RequestQueue queue = Volley.newRequestQueue(ParcelDetailsActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    trackNumFromDB.setText(response.getString("trackingNum"));
                    statusFromDB.setText(response.getString("parcelStatus"));
                    senderNameFromDB.setText(response.getString("senderName"));
                    senderContactFromDB.setText(response.getString("senderContact"));
                    senderAltContactFromDB.setText(response.getString("senderAltContact"));
                    receiverNameFromDB.setText(response.getString("recipientName"));
                    receiverContactFromDB.setText(response.getString("recipientContact"));
                    receiverAltContactFromDB.setText(response.getString("recipientAltContact"));
                    receiverAddressFromDB.setText(new StringBuilder()
                            .append(response.getString("recipientAddressLine1"))
                            .append(",")
                            .append(response.getString("recipientAddressLine2"))
                            .append(",")
                            .append(response.getString("recipientPostcode"))
                            .append(" ")
                            .append(response.getString("recipientCity"))
                            .append(",")
                            .append(response.getString("recipientState")).toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParcelDetailsActivity.this, "Something wrong..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParcelDetailsActivity.this, CallActivity.class);
                startActivity(intent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParcelDetailsActivity.this, SendWhatsappMsgActivity.class);
                startActivity(intent);
            }
        });

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(ParcelDetailsActivity.this);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String recipientAddressLine1 = response.getString("recipientAddressLine1");
                            String recipientAddressLine2 = response.getString("recipientAddressLine2");
                            String recipientPostcode = response.getString("recipientPostcode");
                            String recipientCity = response.getString("recipientCity");
                            String recipientState = response.getString("recipientState");

                            String destinationCityName = recipientAddressLine1+","+recipientAddressLine2+","+recipientPostcode+recipientCity+","+recipientState;

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?f=d&daddr="+destinationCityName));
                            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParcelDetailsActivity.this, "Something wrong..", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);

//                String destinationCityName = "15-19, Jalan 3/109e, Taman Desa Business Park, 58100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur";

//                String destinationCityName = receiverAddressFromDB;
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?f=d&daddr="+destinationCityName));
//                intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
            }
        });

    }

    public void initView() {
        btnCall = findViewById(R.id.btnCall);
        btnMessage = findViewById(R.id.btnMessage);
        btnNavigate = findViewById(R.id.btnNavigate);
        trackNumFromDB = findViewById(R.id.trackNumFromDB);
        statusFromDB = findViewById(R.id.statusFromDB);
        senderNameFromDB = findViewById(R.id.senderNameFromDB);
        senderContactFromDB = findViewById(R.id.senderContactFromDB);
        senderAltContactFromDB = findViewById(R.id.senderAltContactFromDB);
        receiverNameFromDB = findViewById(R.id.receiverNameFromDB);
        receiverContactFromDB = findViewById(R.id.receiverContactFromDB);
        receiverAltContactFromDB = findViewById(R.id.receiverAltContactFromDB);
        receiverAddressFromDB = findViewById(R.id.receiverAddressFromDB);
    }
}