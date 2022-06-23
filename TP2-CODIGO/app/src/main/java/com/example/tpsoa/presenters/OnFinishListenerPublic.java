package com.example.tpsoa.presenters;

import com.example.tpsoa.dtos.responses.PublicApiResponse;

import java.util.List;

public interface OnFinishListenerPublic {
        void onFinished(int code, List<PublicApiResponse> data);
        void onFinished(int code, String result);
        void onFailure(Throwable t);
        void showToast(String message);
}
