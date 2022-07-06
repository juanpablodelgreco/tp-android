package com.example.tpsoa.views;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpsoa.R;
import com.example.tpsoa.presenters.TransactionsPresenterImp;

public class TransactionsViewImp extends Activity implements TransactionsView{
    private TransactionsPresenterImp presenter;
    private TextView transactionsText;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        transactionsText = (TextView) findViewById(R.id.transactionsText);
        findViewById(R.id.getTransactionsButtonId).setOnClickListener(listenerButtons);
        findViewById(R.id.buyButtonId).setOnClickListener(listenerButtons);
        handler = new Handler();
        presenter = new TransactionsPresenterImp(this, handler);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopShowTransactions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopShowTransactions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stopShowTransactions();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void appendText(String message) {
       transactionsText.append(message);
    }

    @Override
    public void clearText() {
        transactionsText.setText("");
    }

    @Override
    public int getTextSize() {
        return transactionsText.length();
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.getTransactionsButtonId:
                    presenter.showTransactions();
                    break;
                case R.id.buyButtonId:
                    presenter.buyTransaction();
                    presenter.showToast("Compra Realizada");
                    break;
            }
        }
    };

}
