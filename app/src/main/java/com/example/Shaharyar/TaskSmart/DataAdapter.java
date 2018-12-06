package com.example.Shaharyar.TaskSmart;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.security.AccessController.getContext;

public class DataAdapter extends AsyncTask<String, String, String> {
        private ReminderDataHelper db;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String data = params[1]; //data to post
            OutputStream out = null;
            db = new ReminderDataHelper(getContext());
            try {
                Cursor res = db.GetAlltags();



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
            return urlString;
        }




}


