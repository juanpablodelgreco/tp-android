package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tpsoa.R;

public class HomeViewImp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.historyButtonId).setOnClickListener(listenerButtons);
    }

    private void navigateToHistory(){
        Intent intent = new Intent(this, HistoryViewImp.class);
        startActivity(intent);
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.historyButtonId:
                    navigateToHistory();
                    break;
            }

        }
    };
}
