package com.example.tpsoa.presenters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.models.HistoryInteractor;
import com.example.tpsoa.models.HomeInteractor;
import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.HistoryView;
import com.example.tpsoa.views.LoginView;

import java.util.Map;

public class HistoryPresenterImp implements HistoryPresenter{
    private HistoryView historyView;
    private HistoryInteractor historyInteractor;

    public HistoryPresenterImp(HistoryView historyView, HistoryInteractor historyInteractor){
        this.historyView = historyView;
        this.historyInteractor = historyInteractor;
    }

    @Override
    public void showToast(String message) {
        historyView.showToast(message);
    }

    @Override
    public void getData(Context ctx) {
        Map<String, ?> data = this.historyInteractor.getData(ctx);
        historyView.generateRows(data);
    }

    @Override
    public Accelerometer getAccelerometer(Activity acc, SensorManager sManager) {
        Accelerometer accelerometer = new Accelerometer(acc, sManager);
        if(!accelerometer.isSensorExist()){
            historyView.showToast("Acelerómetro no detectado.");
        }

        return accelerometer;
    }

    @Override
    public LightSensor getLightSensor(Activity acc, SensorManager sManager, View view){
        LightSensor lightSensor = new LightSensor(acc, sManager, view);
        if(!lightSensor.isSensorExist()){
            showToast("Sensor de luz no detectado.");
        }

        return lightSensor;
    }
}
