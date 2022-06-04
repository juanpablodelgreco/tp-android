package com.example.tpsoa.presenters;
import com.example.tpsoa.models.OnFinishListener;

public interface CreateAccountPresenter {
    void createAccount(String name, String lastName, String dni, String email, String password, String commission, String group);
    void onDestroy();
}

