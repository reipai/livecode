package com.reivai.livetest.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.reivai.livetest.network.model.LoginResponse;

public class Session implements SessionManager {

    static final String TOKEN = "token";

    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public Session(Context context) {
        this.mPref = context.getSharedPreferences("StoryApp", 0);
        this.mEditor = mPref.edit();
    }

    public static Session create(Context context) {
        return new Session(context);
    }

    @Override
    public void saveToken(LoginResponse data) {
        Gson gson = new Gson();
        String token = gson.toJson(data);
        mEditor.putString(TOKEN, token);
        mEditor.apply();
    }

    @Override
    public LoginResponse getToken() {
        Gson gson = new Gson();
        String json = mPref.getString(TOKEN, null);
        return gson.fromJson(json, LoginResponse.class);
    }

    @Override
    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }
}
