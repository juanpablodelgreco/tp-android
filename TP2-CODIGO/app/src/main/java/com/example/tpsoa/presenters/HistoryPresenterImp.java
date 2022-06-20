package com.example.tpsoa.presenters;

import android.app.Activity;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.HistoryView;
import com.example.tpsoa.views.LoginView;

public class HistoryPresenterImp implements HistoryPresenter{
    private HistoryView historyView;

    public HistoryPresenterImp(HistoryView historyView){
        this.historyView = historyView;
    }

    @Override
    public void showToast(String message) {
        historyView.showToast(message);
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
