package com.example.tpsoa.models;

import android.content.Context;

import com.example.tpsoa.presenters.OnFinishListenerPublic;

public interface HomeInteractor {
    void getData(OnFinishListenerPublic ofs, Context ctx);
}
