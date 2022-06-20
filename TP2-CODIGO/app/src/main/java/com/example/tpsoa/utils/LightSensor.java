package com.example.tpsoa.utils;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

public class LightSensor {

    private SensorManager sManager;
    private boolean sensorExist;
    private SensorEventListener lightEventListener;
    private View view;
    private Context ctx;
    private Sensor sensorA;
    private final int maxValue = 255;
    private final int defaultValue = 50;

    public LightSensor(Context ctx, SensorManager sManager, View view) {
        this.view = view;
        this.ctx = ctx;
        this.sManager = sManager;
        this.sensorExist = setLightSensor();
    }

    private boolean setLightSensor() {
        sensorA = sManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(sensorA == null){
            return false;
        }

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                int newValue = value > maxValue ? defaultValue : maxValue - (int) value;
                view.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        return true;
    }

    public void start(){
        sManager.registerListener(lightEventListener, sensorA, sManager.SENSOR_DELAY_FASTEST);
    }

    public void stop(){
        sManager.unregisterListener(lightEventListener);
    }

    public boolean isSensorExist() {
        return sensorExist;
    }
}
