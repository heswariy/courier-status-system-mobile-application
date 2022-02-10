package com.example.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    EditText editTxtUsername, editTxtPassword;
    //    ArrayList<String> username, password;
    String username, password;
    Button btnLogin;

    String url ="http://heswariy-001-site1.dtempurl.com/api/Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.hide();

        editTxtUsername = findViewById(R.id.editTxtUsername);
        editTxtPassword = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
//        String username = editTxtUsername.getText().toString();
//        String password = editTxtPassword.getText().toString();

        try {
            JSONArray jsonArray = new JSONArray(JsonDataFromAsset());
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        username = editTxtUsername.getText().toString();
                        password = editTxtPassword.getText().toString();
                        try {
                            if (object.getString("username").equals(username) && object.getString("password").equals(password)){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid username or password ", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String JsonDataFromAsset(){
        String json = null;
        try {
            InputStream inputStream = getAssets().open("login.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}