package com.example.tpsoa.presenter;

import com.example.tpsoa.model.LoginInteractor;
import com.example.tpsoa.view.LoginView;

public class LoginPresenterImp implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;


    public LoginPresenterImp(LoginView loginView, LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if(loginView != null){
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void onUsernameError() {
        if(loginView != null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if(loginView != null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView != null){
            loginView.navigateToHome();
            loginView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

}
