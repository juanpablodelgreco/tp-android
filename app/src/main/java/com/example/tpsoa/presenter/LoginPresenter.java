package com.example.tpsoa.presenter;

public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}
