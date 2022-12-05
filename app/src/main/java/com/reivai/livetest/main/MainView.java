package com.reivai.livetest.main;

import com.reivai.livetest.network.model.UserResponse;

public interface MainView {

    void success(UserResponse response);

    void error(String message);
}
