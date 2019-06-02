package com.sahabatdeveloper.nostranews.network;

import com.sahabatdeveloper.nostranews.config.AppConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiInterface service;

    public static ApiInterface getClient() {
        service = null;
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = client.create(ApiInterface.class);
        }
        return service;
    }
}
