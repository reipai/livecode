package com.reivai.livetest.network;

import com.reivai.livetest.network.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface NetworkInterface {

    @POST("/login")
    Observable<LoginResponse> login (@Field("email") String email,
                                     @Field("password") String password);

}
