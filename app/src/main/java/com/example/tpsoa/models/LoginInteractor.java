package com.example.tpsoa.models;

import android.content.Context;

public interface LoginInteractor {
    void login(OnFinishListener ofs, String username, String password);
}


