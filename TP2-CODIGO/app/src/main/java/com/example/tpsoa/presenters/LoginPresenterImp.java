package com.example.tpsoa.presenters;

import android.content.Context;

import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.views.LoginView;

public class LoginPresenterImp implements LoginPresenter, OnFinishListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView, LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(Context ctx, String email, String password) {
        if(loginView != null){
            loginView.showProgress();
        }

        loginInteractor.login(this, ctx, email, password);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        loginView.hideProgress();
        if(code == 200){
            loginView.navigateToHome();
        }else{
            loginView.showToast("Error al loguearse.");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        this.showToast("Fallo el envío del login.");
    }

    @Override
    public void showToast(String message) {
        loginView.hideProgress();
        loginView.showToast(message);
    }
}
