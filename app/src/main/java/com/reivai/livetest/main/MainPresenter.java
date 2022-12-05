package com.reivai.livetest.main;

import android.content.Context;

import com.reivai.livetest.network.NetworkApi;
import com.reivai.livetest.network.model.UserResponse;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterImp{

    MainView view;
    Context context;

    public MainPresenter(MainView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getUser(String token, int page) {
        final Observable<UserResponse> userApi = NetworkApi.getNetworkClient(context)
                .getUser(token, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        DisposableObserver<UserResponse> ua = userApi.subscribeWith(new DisposableObserver<UserResponse>() {
            @Override
            public void onNext(UserResponse response) {
                view.success(response);
            }

            @Override
            public void onError(Throwable e) {
                view.error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
