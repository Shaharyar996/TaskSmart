package com.example.Shaharyar.TaskSmart;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.Map;

/**
 * Created by shoaibmeghani on 09/08/2017.
 */

public class RequestHandler {

    private static RequestHandler mInstance;


    public static RequestHandler getInstance(){
        if (mInstance == null)
            mInstance = new RequestHandler();

        return mInstance;
    }

    private RequestHandler(){

    }

    public static void sendGetRequest(final ServiceTypes serviceType,
                                      Map<String, String> params,
                                      final ResponseListener responseListener, String url, Context context, int timeout) {

        Ion.with(context)
                .load(url).setTimeout(timeout).asString().withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {

                        if (result != null) {
                            responseListener.onResponseListener(serviceType,result);

                        } else if (e != null) {
                            responseListener.onErrorListener(serviceType, e, 0, e.getMessage());
                        }

                    }
                });

    }

}
