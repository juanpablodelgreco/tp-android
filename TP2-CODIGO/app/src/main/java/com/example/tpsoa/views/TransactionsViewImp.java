package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tpsoa.R;
import com.example.tpsoa.presenters.TransactionsPresenterImp;

public class TransactionsViewImp extends Activity implements TransactionsView{
    private TransactionsPresenterImp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

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

    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.getTransactionsButtonId:
                    presenter.showToast("Mostrar Transacciones");
                    break;
                case R.id.buyButtonId:
                    presenter.showToast("Compra Realizada");
                    break;
            }
        }
    };
}
