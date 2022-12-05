package com.reivai.livetest.login;

import com.reivai.livetest.network.model.LoginResponse;

public interface LoginView {

    void success(LoginResponse response);

    void error(String error);
}
