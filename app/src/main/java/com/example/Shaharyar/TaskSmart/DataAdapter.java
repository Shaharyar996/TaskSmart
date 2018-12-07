package com.example.Shaharyar.TaskSmart;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;


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
        public DataAdapter(Context context)
        {
            this.mContext = context;
        }


        @Override
        protected  String doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String data = params[1]; //data to post
            OutputStream out = null;
            db = new ReminderDataHelper(mContext);
            try {
                Cursor res = db.GetAlltags();
                StringBuffer str = new StringBuffer();
                while (res.moveToNext())
                {
                    str.append(res.getString(res.getColumnIndex("location")));
                    Log.i(TAG, "doInBackground: "+res.getString(res.getColumnIndex("location")));

                }




            }
            catch (Exception e) {
                System.out.println(e.getMessage());

            }
           try {

                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                out = new BufferedOutputStream(urlConnection.getOutputStream());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                out.close();

                urlConnection.connect();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return urlString="www.google.com";
        }




}


