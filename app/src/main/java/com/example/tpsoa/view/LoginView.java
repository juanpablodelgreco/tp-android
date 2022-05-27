package com.example.tpsoa.view;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void setUsernameError();
    void setPasswordError();
    void navigateToHome();
    void navigateToCreateAccount();
}
