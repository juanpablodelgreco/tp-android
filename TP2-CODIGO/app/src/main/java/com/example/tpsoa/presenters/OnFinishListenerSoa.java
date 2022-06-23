package com.example.tpsoa.presenters;

import com.example.tpsoa.dtos.responses.PublicApiResponse;

public interface OnFinishListenerSoa {
        void onFinished(int code, String result);
        void onFailure(Throwable t);
        void showToast(String message);
}
