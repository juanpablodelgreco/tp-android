package com.example.tpsoa.views;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void navigateToCreateAccount();
    void navigateToHome();
    void showToast(String message);
}
