package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListenerSoa;

public interface AuthenticationInteractor {
    void send(Context ctx, String number);
    void verify(Context ctx, String code);
}
