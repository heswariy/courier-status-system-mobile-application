package com.example.cms;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DataService {

    public static final String QUERY_FOR_LOGIN_API = "http://heswariy-001-site1.dtempurl.com/api/Parcel/";

    Context context;
    String parcelDetails;

    public DataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String parcelDetails);
    }

    public void getParcelStatus(String trackingNum, VolleyResponseListener volleyResponseListener){

        String url = QUERY_FOR_LOGIN_API + trackingNum;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parcelDetails = "";

                try {
                    parcelDetails = response.getString("parcelStatus");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // this worked but did not return the password to Main Activity
                // Toast.makeText(context, "Password = " + password, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(parcelDetails);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(context, "Something wrong..", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong..");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);

        // returned a null!!!!!!! PROBLEM!!!!!!!!!!!!!!!
        // return password;
    }


}
