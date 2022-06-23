package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListenerSoa;

public interface AuthenticationInteractor {
    void send(Context ctx, OnFinishListenerSoa ofs, String number);
    boolean verify(Context ctx, OnFinishListenerSoa ofs, String code);
}
