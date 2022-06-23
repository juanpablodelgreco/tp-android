package com.example.tpsoa.presenters;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.dtos.responses.PublicApiResponse;
import com.example.tpsoa.models.HomeInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, OnFinishListenerPublic {
    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenterImpl(HomeView homeView, HomeInteractor homeInteractor){
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void getData(Context ctx) {
            homeInteractor.getData(this, ctx);
    }

    @Override
    public Accelerometer getAccelerometer(Context ctx, Activity acc, SensorManager sManager) {
        sManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        Accelerometer accelerometer = new Accelerometer(acc, sManager);
        if(!accelerometer.isSensorExist()){
            showToast("Acelerómetro no detectado.");
        }

        return accelerometer;
    }

    @Override
    public LightSensor getLightSensor(Context ctx, Activity acc, SensorManager sManager, View view) {
        sManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        LightSensor lightSensor = new LightSensor(acc, sManager, view);
        if(!lightSensor.isSensorExist()){
            showToast("Sensor de luz no detectado.");
        }

        return lightSensor;
    }

    @Override
    public void onFinished(int code, List<PublicApiResponse> data) {
        homeView.showData(data);
    }

    @Override
    public void onFinished(int code, String result) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void showToast(String message) {
        homeView.showToast(message);
    }

}
