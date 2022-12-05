package com.reivai.livetest.network;

import android.content.Context;
import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {

    public static NetworkInterface getNetworkInterface(Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api")
                .client(getclient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(NetworkInterface.class);
    }

    public static OkHttpClient getclient() {
        Interceptor interceptor = chain -> {
            SystemClock.sleep(1000);
            return chain.proceed(chain.request());
        };

        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT))
                .addNetworkInterceptor(interceptor)
                .build();
    }
}
