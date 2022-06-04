package com.example.tpsoa.views;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void showErrorMessage(String message);
    void navigateToCreateAccount();
}
