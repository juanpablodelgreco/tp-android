package com.example.tpsoa.views;

import com.example.tpsoa.dtos.responses.PublicApiResponse;

import java.util.List;

public interface HomeView {
    void navigateToHistory();
    void showData(List<PublicApiResponse> data);
    void showToast(String message);
}
