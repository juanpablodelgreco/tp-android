package com.example.tpsoa.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class HistoryInteractorImp implements HistoryInteractor{
    @Override
    public Map<String, ?> getData(Context ctx) {
        SharedPreferences sp = ctx.getApplicationContext().getSharedPreferences("log", 0);
        return sp.getAll();
    }
}
