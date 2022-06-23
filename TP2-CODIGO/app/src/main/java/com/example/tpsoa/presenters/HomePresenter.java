package com.example.tpsoa.presenters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

public interface HomePresenter {
    void getData(Context ctx);
    Accelerometer getAccelerometer(Activity acc, SensorManager sManager);
    LightSensor getLightSensor(Activity acc, SensorManager sManager, View view);
}
