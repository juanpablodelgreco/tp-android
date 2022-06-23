package com.example.tpsoa.presenters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.models.AuthenticationInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.AuthenticationView;

public class AuthenticationPresenterImp implements AuthenticationPresenter, OnFinishListenerSoa {
    private AuthenticationView authenticationView;
    private AuthenticationInteractor authenticationInteractor;

    public AuthenticationPresenterImp(AuthenticationView authenticationView, AuthenticationInteractor authenticationInteractor){
        this.authenticationView = authenticationView;
        this.authenticationInteractor = authenticationInteractor;
    }

    @Override
    public void sendCode(Context ctx, String numberPhone) {
        authenticationInteractor.send( ctx, this, numberPhone);
    }

    @Override
    public void verifyCode(Context ctx, String code) {
        if(authenticationInteractor.verify( ctx, this, code)){
            authenticationView.navigateToLogin();
        }else{
            this.showToast("Código de verificación erróneo.");
        }
    }

    @Override
    public void onDestroy() {
        authenticationView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        if(code == 200){
            authenticationView.navigateToLogin();
        }else{
            this.showToast(result);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        this.showToast("Falló al crear usuario.");
    }

    @Override
    public void showToast(String message) {
        authenticationView.showToast(message);
    }

    @Override
    public Accelerometer getAccelerometer(Activity acc, SensorManager sManager){
        Accelerometer accelerometer = new Accelerometer(acc, sManager);
        if(!accelerometer.isSensorExist()){
            showToast("Acelerómetro no detectado.");
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
