package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListener;

public interface LoginInteractor {
    void login(OnFinishListener ofs, Context ctx, String username, String password);
}


