package com.example.tpsoa.presenters;

import com.example.tpsoa.models.CreateAccountInteractor;
import com.example.tpsoa.models.OnFinishListener;
import com.example.tpsoa.views.CreateAccountView;

public class CreateAccountPresenterImp implements CreateAccountPresenter, OnFinishListener {
    private CreateAccountView createAccountView;
    private CreateAccountInteractor createAccountInteractor;

    public CreateAccountPresenterImp(CreateAccountView createAccountView, CreateAccountInteractor createAccountInteractor){
        this.createAccountView = createAccountView;
        this.createAccountInteractor = createAccountInteractor;
    }

    @Override
    public void createAccount(String name, String lastName, String dni, String email, String password, String comission, String group) {
        createAccountInteractor.createAccount(this, name, lastName, dni, email, comission, password, group);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onFinished(int code, String result) {
        if(code == 200){
            createAccountView.navigateToLogin();
        }else{
            createAccountView.showErrorMessage(result);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onValidationFieldFail(String message) {
        createAccountView.showErrorMessage(message);
    }
}
