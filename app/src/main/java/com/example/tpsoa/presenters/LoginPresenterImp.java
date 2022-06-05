package com.example.tpsoa.presenters;

import android.content.Context;
import android.util.Log;

import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.models.OnFinishListener;
import com.example.tpsoa.views.LoginView;

public class LoginPresenterImp implements LoginPresenter, OnFinishListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView, LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String email, String password) {
        if(loginView != null){
            loginView.showProgress();
        }
        loginInteractor.login(this, email, password);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        loginView.hideProgress();
        if(code == 200){
            loginView.navigateToCreateAccount();
        }else{
            loginView.showErrorMessage(result);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onValidationFieldFail(String message) {
        loginView.hideProgress();
        loginView.showErrorMessage(message);
    }
}
