package com.example.tpsoa.utils;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

public class LightSensor implements SensorEventListener {

    private SensorManager sManager;
    private View view;
    private Sensor sensor;
    private final int maxValue = 255;
    private final int defaultValue = 50;

    public LightSensor(SensorManager sManager, View view) {
        this.view = view;
        this.sensor = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.sManager = sManager;
    }

    public void start(){
        sManager.registerListener(this, sensor, sManager.SENSOR_DELAY_FASTEST);
    }

    public void stop(){
        sManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        int newValue = value > maxValue ? defaultValue : maxValue - (int) value;
        Log.i("value", String.valueOf(newValue));
        view.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
