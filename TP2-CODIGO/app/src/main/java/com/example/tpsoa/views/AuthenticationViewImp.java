package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tpsoa.R;
import com.example.tpsoa.models.AuthenticationInteractorImp;
import com.example.tpsoa.presenters.AuthenticationPresenter;
import com.example.tpsoa.presenters.AuthenticationPresenterImp;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

public class AuthenticationViewImp extends Activity implements AuthenticationView {

    private EditText codeField;
    private EditText phoneNumberField;
    private AuthenticationPresenter presenter;
    private View constraintLayoutAuthentication;
    private SensorManager sManager;
    private Accelerometer accelerometer;
    private LightSensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        codeField = findViewById(R.id.textViewCodigo);
        phoneNumberField = findViewById(R.id.editTextNumero);
        findViewById(R.id.buttonEnviarCodigo).setOnClickListener(listenerButtons);
        findViewById(R.id.buttonVerificarCodigo).setOnClickListener(listenerButtons);
        constraintLayoutAuthentication = findViewById(R.id.constraintLayoutAuthentication);
        presenter = new AuthenticationPresenterImp(this, new AuthenticationInteractorImp());
        accelerometer = presenter.getAccelerometer(getApplicationContext(), this, sManager);
        lightSensor = presenter.getLightSensor(getApplicationContext(),this, sManager, constraintLayoutAuthentication);
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

    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonEnviarCodigo:
                    presenter.sendCode(getApplicationContext(), phoneNumberField.getText().toString());
                    break;
                case R.id.buttonVerificarCodigo:
                    presenter.verifyCode(getApplicationContext(), codeField.getText().toString());
            }
        }
    };

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginViewImp.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
