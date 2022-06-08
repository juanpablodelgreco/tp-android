package com.example.tpsoa.presenters;

import android.content.Context;

import com.example.tpsoa.models.CreateAccountInteractor;
import com.example.tpsoa.views.CreateAccountView;

public class CreateAccountPresenterImp implements CreateAccountPresenter, OnFinishListener {
    private CreateAccountView createAccountView;
    private CreateAccountInteractor createAccountInteractor;

    public CreateAccountPresenterImp(CreateAccountView createAccountView, CreateAccountInteractor createAccountInteractor){
        this.createAccountView = createAccountView;
        this.createAccountInteractor = createAccountInteractor;
    }

    @Override
    public void createAccount(Context ctx, String name, String lastName, String dni, String email, String password, String commission, String group) {
        createAccountInteractor.createAccount(this, ctx, name, lastName, dni, email, commission, password, group);
    }

    @Override
    public void onDestroy() {
        createAccountView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        if(code == 200){
            createAccountView.navigateToLogin();
        }else{

            this.showToast(result);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        this.showToast("Falló al crear usuario.");
    }

    @Override
    public void showToast(String message) {
        createAccountView.showToast(message);
    }
}
