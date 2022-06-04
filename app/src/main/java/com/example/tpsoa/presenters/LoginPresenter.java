package com.example.tpsoa.presenters;

import android.content.Context;

public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}
