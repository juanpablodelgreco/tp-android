package com.example.tpsoa.models;

import android.content.Context;

public interface LoginInteractor {

    interface OnFinishListener {
        void onFinished(int code, String result);
        void onFailure(Throwable t);
        void onValidationFieldFail(String message);
    }

    void login(OnFinishListener ofs, String username, String password);
}


