package com.example.tpsoa.presenters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;
import com.example.tpsoa.models.HistoryInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.HistoryView;
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
    public Accelerometer getAccelerometer(Context ctx, Activity acc, SensorManager sManager) {
        sManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        Accelerometer accelerometer = new Accelerometer(acc, sManager);
        return accelerometer;
    }

    @Override
    public LightSensor getLightSensor(Context ctx, Activity acc, SensorManager sManager, View view) {
        sManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        LightSensor lightSensor = new LightSensor(sManager, view);
        return lightSensor;
    }
}
