package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListener;

public interface CreateAccountInteractor {
    void createAccount(OnFinishListener ofs, Context ctx, String firstName, String lastName, String dni, String email, String comission, String password, String group);
}
