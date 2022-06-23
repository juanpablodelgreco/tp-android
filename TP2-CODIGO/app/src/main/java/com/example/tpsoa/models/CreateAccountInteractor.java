package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListenerSoa;

public interface CreateAccountInteractor {
    void createAccount(OnFinishListenerSoa ofs, Context ctx, String firstName, String lastName, String dni, String email, String comission, String password, String group);
}
