package com.example.tpsoa.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.tpsoa.R;

public class HomeActivityImp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Ejecuto", "onCreate main Activity");
    }
}