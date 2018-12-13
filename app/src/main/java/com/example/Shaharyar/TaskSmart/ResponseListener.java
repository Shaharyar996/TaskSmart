package com.example.Shaharyar.TaskSmart;

import com.koushikdutta.ion.Response;

public interface ResponseListener {

    public void onResponseListener(ServiceTypes serviceType, Response<String> response);
    public void onErrorListener(ServiceTypes serviceType, Exception e, int code, String msg);
}

