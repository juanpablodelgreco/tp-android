package com.example.tpsoa.presenters;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.tpsoa.views.TransactionsView;

import java.util.Random;

public class TransactionsPresenterImp implements TransactionsPresenter{
    private TransactionsView transactionsView;

    public TransactionsPresenterImp(TransactionsView transactionsView){
        this.transactionsView = transactionsView;
    }

    @Override
    public void showToast(String message) {
        transactionsView.showToast(message);
    }

    @Override
    public void showTransactions() {
        int i = 1;
        while (i < 100) {
            transactionsView.showTrx(getTransaction());
            Log.d("Trx", getTransaction());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public static String getTransaction() {
        Random random = new Random();
        int transactionAmount = random.nextInt(100000-10) + 10;

        return "Se compraron: "+String.valueOf(transactionAmount)+"$";
    }


}
