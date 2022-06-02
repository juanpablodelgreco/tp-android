package com.example.tpsoa.presenters;

import android.content.Context;
import android.util.Log;

import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.views.LoginView;

public class LoginPresenterImp implements LoginPresenter {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView, LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(Context ct, String email, String password) {
        if(loginView != null){
            loginView.showProgress();
        }
        loginInteractor.login(ct, email, password);
    }
}
