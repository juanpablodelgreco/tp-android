package com.example.tpsoa.presenters;

public interface OnFinishListener {
        void onFinished(int code, String result);
        void onFailure(Throwable t);
        void showToast(String message);
}
