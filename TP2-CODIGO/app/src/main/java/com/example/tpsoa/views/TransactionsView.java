package com.example.tpsoa.views;

public interface TransactionsView {
    void showToast(String message);
    void appendText(String message);
    int getTextSize();
    void clearText();
}
