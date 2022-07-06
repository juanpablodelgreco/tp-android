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

    public AuthenticationPresenterImp(AuthenticationView authenticationView, AuthenticationInteractor authenticationInteractor) {
        this.authenticationView = authenticationView;
        this.authenticationInteractor = authenticationInteractor;
    }

    @Override
    public void sendCode(Context ctx, String numberPhone) {
        authenticationInteractor.send(ctx, this, numberPhone);
    }

    @Override
    public void verifyCode(Context ctx, String code) {
        if (authenticationInteractor.verify(ctx, this, code)) {
            authenticationView.navigateToLogin();
        } else {
            this.showToast("Código de verificación erróneo.");
        }
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

    @Override
    public void onDestroy() {
        authenticationView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        if (code == 200) {
            authenticationView.navigateToLogin();
        } else {
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
}

