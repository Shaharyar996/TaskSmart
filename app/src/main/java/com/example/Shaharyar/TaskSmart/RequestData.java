package com.example.Shaharyar.TaskSmart;

import com.google.gson.JsonObject;

import java.util.Map;

public class RequestData {

    public static final int GET = 1;
    public static final int POST = 2;
    public static final int JSON = 3;

    public static final int MULTIPART = 4;
    public static final int CUSTOM=5;
    public static final int JSON2 = 6;
    public static final int PUT = 7;
    public static final int DELETE = 8;
    public static final int ASYNCWITHAUTH = 9;


    public int method;

    public Map<String, ?> params;

    public int serviceType;

    public ResponseListener responseListener;

    public int timeout;

    public String url;

    public JsonObject json;

    public RequestData(){
        this.timeout=20000;
    }
}
