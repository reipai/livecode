package com.reivai.livetest.login;

import android.content.Context;

import com.reivai.livetest.network.NetworkApi;
import com.reivai.livetest.network.NetworkInterface;
import com.reivai.livetest.network.model.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginPresenterImp{

    LoginView view;
    Context context;

    public LoginPresenter(LoginView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(String email, String password) {
        final Observable<LoginResponse> loginApi = NetworkApi.getNetworkClient(context)
                .login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        DisposableObserver<LoginResponse> api =loginApi.subscribeWith(new DisposableObserver<LoginResponse>() {
            @Override
            public void onNext(LoginResponse response) {
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
