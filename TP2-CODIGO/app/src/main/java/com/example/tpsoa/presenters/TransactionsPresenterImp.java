package com.example.tpsoa.presenters;
import android.os.Handler;
import android.util.Log;
import com.example.tpsoa.views.TransactionsView;

import java.util.Random;

public class TransactionsPresenterImp implements TransactionsPresenter {
    private TransactionsView transactionsView;
    private android.os.Handler handler;
    private SaleDolar bctrx;
    private BuyDolar dltrx;
    private ClearText clTx;
    private ManualPurchase mp;
    public static boolean running;

    public TransactionsPresenterImp(TransactionsView transactionsView, Handler handler){
        this.transactionsView = transactionsView;
        this.handler = handler;
        bctrx = new SaleDolar();
        dltrx = new BuyDolar();
        clTx = new ClearText();
        mp = new ManualPurchase();
    }

    @Override
    public void showToast(String message) {
        transactionsView.showToast(message);
    }

    @Override
    public void showTransactions() {
        running = true;
        new Thread(bctrx).start();
        new Thread(dltrx).start();
        new Thread(clTx).start();
    }

    public void stopShowTransactions(){
        running = false;
    }

    @Override
    public void buyTransaction(){
        new Thread(mp).start();
    }

    private class SaleDolar implements Runnable {
        @Override
        public void run() {
            while(running){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transactionsView.appendText("Se vendieron: "+ getAmount() +" USD\n");
                        Log.i("Trx", "Venta de USD.");
                    }
                }, 3000);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
    }

    public static int getAmount() {
        Random random = new Random();
        return random.nextInt(100000-10) + 10;
    }

    private class BuyDolar implements Runnable {
        @Override
        public void run() {
            while(running){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transactionsView.appendText("Se compraron: "+ getAmount() + " USD\n");
                        Log.i("Trx", "Compra de USD.");
                    }
                }, 2000);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
    }

    private class ClearText implements Runnable {
        @Override
        public void run() {
            while(running){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(transactionsView.getTextSize() > 200){
                            transactionsView.clearText();
                            Log.i("Trx", "Limpiando.");
                        }
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
    }

    private class ManualPurchase implements Runnable {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    transactionsView.appendText("Compraste: " + getAmount() + " USD.\n");
                }
            });
            return;
        }
    }
}
