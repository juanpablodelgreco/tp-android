package com.example.tpsoa.views;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpsoa.R;
import com.example.tpsoa.presenters.TransactionsPresenterImp;

public class TransactionsViewImp extends Activity implements TransactionsView{
    private TransactionsPresenterImp presenter;
    private boolean printTrx;
    private TextView transactionsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        transactionsText = (TextView) findViewById(R.id.transactionsText);
        findViewById(R.id.getTransactionsButtonId).setOnClickListener(listenerButtons);
        findViewById(R.id.buyButtonId).setOnClickListener(listenerButtons);
        presenter = new TransactionsPresenterImp(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTrx(String transaction) {
        transactionsText.append(transaction+"\n");

        int linesToRemove = transactionsText.getLineCount() - 9;
        try {
            if (linesToRemove > 0) {
                for (int i = 0; i < linesToRemove; i++) {
                    Editable text = transactionsText.getEditableText();
                    int lineStart = transactionsText.getLayout().getLineStart(0);
                    int lineEnd = transactionsText.getLayout().getLineEnd(0);
                    text.delete(lineStart, lineEnd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.getTransactionsButtonId:
                    presenter.showTransactions();
                    break;
                case R.id.buyButtonId:
                    presenter.showToast("Compra Realizada");
                    break;
            }
        }
    };
}
