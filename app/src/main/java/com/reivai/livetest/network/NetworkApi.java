package com.reivai.livetest.network;

import android.content.Context;
import android.os.SystemClock;

import androidx.viewbinding.BuildConfig;

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

    public static NetworkInterface getNetworkClient(Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(NetworkInterface.class);
    }

    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingeInterceptor())
                .build();
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        } else {
            return HttpLoggingInterceptor.Level.NONE;
        }
    }

    public static HttpLoggingInterceptor getLoggingeInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(getInterceptorLevel());
        return loggingInterceptor;
    }
}
