package com.example.Shaharyar.TaskSmart;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

import Services.Service;

/**
 * Created by Sohail Anwar on 1/12/2018.
 */

public class RestClient {



    private static Service REST_CLIENT;
    static {
        setupRestClient();
    }

    private RestClient() {}

    public static Service get() {
        return REST_CLIENT;
    }

    public static void setupRestClient() {
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30,TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Service.class);
    }

}
