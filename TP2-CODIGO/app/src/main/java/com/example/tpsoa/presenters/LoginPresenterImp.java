package com.example.tpsoa.presenters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;

import com.example.tpsoa.dtos.responses.PublicApiResponse;
import com.example.tpsoa.models.LoginInteractor;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;
import com.example.tpsoa.views.LoginView;

public class LoginPresenterImp implements LoginPresenter, OnFinishListenerSoa {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(LoginView loginView, LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(Context ctx, String email, String password) {
        if(loginView != null){
            loginView.showProgress();
        }

        loginInteractor.login(this, ctx, email, password);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onFinished(int code, String result) {
        loginView.hideProgress();
        if(code == 200){
            loginView.navigateToHome();
        }else{
            loginView.showToast("Error al loguearse.");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        this.showToast("Fallo el envío del login.");
    }

    @Override
    public void showToast(String message) {
        loginView.hideProgress();
        loginView.showToast(message);
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
