package com.reivai.livetest.session;

import com.reivai.livetest.network.model.LoginResponse;

public interface SessionManager {

    void saveToken(LoginResponse data);

    LoginResponse getToken();

    void clear();
}
