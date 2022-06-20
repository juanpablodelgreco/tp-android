package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListenerSoa;

public interface LoginInteractor {
    void login(OnFinishListenerSoa ofs, Context ctx, String username, String password);
    void registerActivity(Context ctx, String username);
}


