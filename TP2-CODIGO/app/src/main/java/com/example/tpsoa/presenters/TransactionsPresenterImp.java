package com.example.tpsoa.presenters;

import android.app.Activity;
import android.view.View;

import com.example.tpsoa.views.TransactionsView;

public class TransactionsPresenterImp implements TransactionsPresenter{
    private TransactionsView transactionsView;

    public TransactionsPresenterImp(TransactionsView transactionsView){
        this.transactionsView = transactionsView;
    }

    @Override
    public void showToast(String message) {
        transactionsView.showToast(message);
    }
}
