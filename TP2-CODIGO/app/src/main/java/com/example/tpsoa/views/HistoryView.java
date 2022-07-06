package com.example.tpsoa.views;

import java.util.Map;

public interface HistoryView {
    void showToast(String message);
    void generateRows(Map<String, ?> data);
}
