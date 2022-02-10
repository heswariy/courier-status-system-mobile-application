package com.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScannerActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private CodeScanner mCodeScanner;
    boolean CameraPermission = false;
    final int CAMERA_PERM = 1;
    TextView tv_textView;
    ListView listview;
    Button btn_done;
    ArrayList<String> trackingNum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0600a8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle("Pick-Up Scan");

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        btn_done = findViewById(R.id.btn_done);
        tv_textView = findViewById(R.id.tv_textView);
        listview = findViewById(R.id.listview);
        mCodeScanner = new CodeScanner(this, scannerView);

        askPermission();
        if (CameraPermission){
            scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCodeScanner.startPreview();
                }
            });

            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {//
                            tv_textView.setText(result.getText());

                            RequestQueue queue = Volley.newRequestQueue(ScannerActivity.this);
                            String url ="http://heswariy-001-site1.dtempurl.com/api/Parcel/" + result.getText();

                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        int id = response.getInt("id");
                                        if (id != 0) {
                                            trackingNum.add(result.getText());

                                            ArrayAdapter<String> trackingAdapter = new ArrayAdapter<String>(ScannerActivity.this, android.R.layout.simple_list_item_1,trackingNum);
                                            listview.setAdapter(trackingAdapter);
                                        } else {
                                            Toast.makeText(ScannerActivity.this, "Tracking number does not exist", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ScannerActivity.this, "Something wrong..", Toast.LENGTH_SHORT).show();
                                }
                            });
                            queue.add(request);

//                            trackingNum.add(result.getText());
//
//                            ArrayAdapter<String> trackingAdapter = new ArrayAdapter<String>(ScannerActivity.this, android.R.layout.simple_list_item_1,trackingNum);
//                            listview.setAdapter(trackingAdapter);

                            listview.setOnItemClickListener((new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Toast.makeText(ScannerActivity.this, trackingNum.get(position)+" Selected", Toast.LENGTH_SHORT).show();
                                }
                            }));

                        }
                    });
                }
            });
        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerActivity.this, PickupScanActivity.class);
                intent.putExtra("trackingList", trackingNum);
                startActivity(intent);
            }
        });
    }

    private void askPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ScannerActivity.this, new String[]{Manifest.permission.CAMERA},CAMERA_PERM);

            }else {
                mCodeScanner.startPreview();
                CameraPermission = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA_PERM){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mCodeScanner.startPreview();;
                CameraPermission = true;
            }else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){

                    new AlertDialog.Builder(this)
                            .setTitle("Permission")
                            .setMessage("Please provide the camera permission for using all the features of the app")
                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    ActivityCompat.requestPermissions(ScannerActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }else {

                    new AlertDialog.Builder(this)
                            .setTitle("Permission")
                            .setMessage("You have denied some permission. Allow all permission at [Settings] > [Permissions]")
                            .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }).setNegativeButton("No, Exit app", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).create().show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause(){
        if (CameraPermission){
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }
}