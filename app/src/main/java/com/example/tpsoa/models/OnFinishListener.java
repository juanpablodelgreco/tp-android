package com.example.tpsoa.models;

public interface OnFinishListener {
        void onFinished(int code, String result);
        void onFailure(Throwable t);
        void onValidationFieldFail(String message);
}
