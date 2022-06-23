package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.tpsoa.R;
import com.example.tpsoa.models.LoginInteractorImp;
import com.example.tpsoa.presenters.LoginPresenter;
import com.example.tpsoa.presenters.LoginPresenterImp;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

public class LoginViewImp extends Activity implements LoginView {

    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private LoginPresenter presenter;
    private View constraintLayoutLogin;
    private SensorManager sManager;
    private Accelerometer accelerometer;
    private LightSensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.loginInputEmail);
        password = findViewById(R.id.loginInputPassword);
        progressBar = findViewById(R.id.progressBar);
        constraintLayoutLogin = findViewById(R.id.constraintLayoutLogin);
        findViewById(R.id.loginButton).setOnClickListener(listenerButtons);
        findViewById(R.id.loginCreateAccountButton).setOnClickListener(listenerButtons);
        presenter = new LoginPresenterImp(this, new LoginInteractorImp());
        sManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = presenter.getAccelerometer(this, sManager);
        lightSensor = presenter.getLightSensor(this, sManager, constraintLayoutLogin);
        Log.i("Ejecuto", "onCreate login Activity");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Ejecuto", "onStart login Activity");
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerometer.start();
        lightSensor.start();
        Log.i("Ejecuto", "onResume login Activity");
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onPause login Activity");
    }

    @Override
    protected void onStop(){
        super.onStop();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onStop login Activity");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onDestroy login Activity");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() { progressBar.setVisibility(View.INVISIBLE);}

    @Override
    public void navigateToCreateAccount() {
        Intent intent = new Intent(this, CreateAccountViewImp.class);
        startActivity(intent);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, HomeViewImp.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginButton:
                        presenter.validateCredentials(getApplicationContext(), email.getText().toString(), password.getText().toString());
                    break;
                case R.id.loginCreateAccountButton:
                    navigateToCreateAccount();
            }

        }
    };
}