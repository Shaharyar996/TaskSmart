package com.example.Shaharyar.TaskSmart;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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
    protected String doInBackground(String... params)  {
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
        String url = "http://192.168.0.102/api/values";

        JSONArray Waypoints = new JSONArray();
        JSONArray tags =new JSONArray();
        for (String i : tag){
            tags.put(i);
        }
        final JSONObject json  = new JSONObject();
        try {
            json.put("waypoints",Waypoints);
            json.put("tags",tags);
            json.put("curLoc","24.933043,67.045073");
            json.put("dest","24.901511,67.055182");

            final BufferedReader[] br = {null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://192.168.0.103/api/values");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);


                        Log.i("JSON", json.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());

                        os.writeBytes(json.toString());

                        os.flush();
                        os.close();

                        Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                        Log.i("MSG" , conn.getResponseMessage());
                        Log.i("RES", String.valueOf(conn.getInputStream()));

                        if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
                            br[0] = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        } else {
                            br[0] = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        }
                        String sCurrentLine;

                        for(int i=0;i<br.length;i++) {
                            System.out.println(br[i]);
                            sCurrentLine=br[i].toString();
                        }

                        //Log.i("SB",sb.toString());

                        conn.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        final JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                System.out.println(response.toString());


        //This code is executed if the server responds, whether or not the response contains data.
        //The String 'response' contains the server's response.
//            }
//        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                System.out.println(error.getMessage());
//
//
//                //This code is executed if there is an error.
//            }
//        });

//        MyRequestQueue.add(MyStringRequest);
        return  "www.google.com";
    }

    public  class customClass {
        public  String test1;
        public  String test2;
    }

}
