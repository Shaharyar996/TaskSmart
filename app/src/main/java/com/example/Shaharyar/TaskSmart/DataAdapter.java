package com.example.Shaharyar.TaskSmart;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class DataAdapter extends AsyncTask<String, String, String> {

    StringBuffer str = new StringBuffer();
    private ReminderDataHelper db;
    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public DataAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    protected String doInBackground(String... params) {
        final List<String> tag = new ArrayList<>();
        db = new ReminderDataHelper(mContext);
        try {
            Cursor res = db.GetAlltags();
            while (res.moveToNext()) {
                //str.append(res.getString(res.getColumnIndex("tags")));
                tag.add(res.getString((res.getColumnIndex("tags"))));
              //  Log.i(TAG, "doInBackground: " + res.getString(res.getColumnIndex("location")));

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(mContext);
        String url = "http://192.168.0.106//api/values";
        JSONObject object = new JSONObject();
        try {

            object.put("waypoints", new ArrayList<String>());
            object.put("tags", tag);
            object.put("curLoc", "Value");
            object.put("dest", "Value");
        }
        catch(Exception e){

        }

        JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error.getMessage());


                //This code is executed if there is an error.
            }
        });

        MyRequestQueue.add(MyStringRequest);
        return  "www.google.com";
    }

    public  class customClass {
        public  String test1;
        public  String test2;
    }

}


