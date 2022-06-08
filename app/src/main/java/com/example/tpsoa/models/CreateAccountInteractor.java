package com.example.tpsoa.models;

import com.example.tpsoa.presenters.OnFinishListener;

public interface CreateAccountInteractor {
    void createAccount(OnFinishListener ofs, String firstName, String lastName, String dni, String email, String comission, String password, String group);
}
