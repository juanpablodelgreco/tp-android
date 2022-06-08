package com.example.tpsoa.presenters;

import android.content.Context;

public interface CreateAccountPresenter {
    void createAccount(Context ctx, String name, String lastName, String dni, String email, String password, String commission, String group);
    void onDestroy();
}

