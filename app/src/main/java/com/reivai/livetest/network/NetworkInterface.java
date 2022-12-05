package com.reivai.livetest.network;

import com.reivai.livetest.network.model.LoginResponse;
import com.reivai.livetest.network.model.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkInterface {

    @FormUrlEncoded
    @POST("/api/login")
    Observable<LoginResponse> login (@Field("email") String email,
                                     @Field("password") String password);

    @GET("/api/users")
    Observable<UserResponse> getUser(@Header("Authorization") String token,
                                     @Query("page") int page);
}
