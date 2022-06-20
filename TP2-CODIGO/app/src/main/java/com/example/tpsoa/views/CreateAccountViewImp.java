package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpsoa.R;
import com.example.tpsoa.models.CreateAccountInteractorImp;
import com.example.tpsoa.presenters.CreateAccountPresenter;
import com.example.tpsoa.presenters.CreateAccountPresenterImp;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

public class CreateAccountViewImp extends Activity implements  CreateAccountView {

    private EditText firstName;
    private EditText lastName;
    private EditText dni;
    private EditText email;
    private EditText commission;
    private EditText password;
    private EditText group;
    private CreateAccountPresenter presenter;
    private SensorManager sManager;
    private Accelerometer accelerometer;
    private LightSensor lightSensor;
    private View contraintLayoutCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstName =(EditText) findViewById(R.id.createAccountInputFirstName);
        lastName =(EditText) findViewById(R.id.createAccountInputLastName);
        dni =(EditText) findViewById(R.id.createAccountInputDNI);
        email =(EditText) findViewById(R.id.createAccountInputEmail);
        commission =(EditText) findViewById(R.id.createAccountInputCommission);
        password =(EditText) findViewById(R.id.createAccountInputPassword);
        group =(EditText) findViewById(R.id.createAccountInputGroup);
        findViewById(R.id.createAccountButton).setOnClickListener(listenerButtons);
        findViewById(R.id.createAccountButtonReturn).setOnClickListener(listenerButtons);
        contraintLayoutCreateAccount = findViewById(R.id.contraintLayoutCreateAccountId);
        presenter = new CreateAccountPresenterImp(this, new CreateAccountInteractorImp());

        sManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = presenter.getAccelerometer(this, sManager);
        lightSensor = presenter.getLightSensor(this, sManager, contraintLayoutCreateAccount);

        Log.i("Ejecuto", "onCreate createAccount Activity");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Ejecuto", "onStart createAccount Activity");
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerometer.start();
        lightSensor.start();
        Log.i("Ejecuto", "onResume createAccount Activity");
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onPause createAccount Activity");
    }

    @Override
    protected void onStop(){
        super.onStop();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onStop createAccount Activity");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
        accelerometer.stop();
        lightSensor.stop();
        Log.i("Ejecuto", "onDestroy createAccount Activity");
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginViewImp.class);
        startActivity(intent);
    }


    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.createAccountButtonReturn:
                    navigateToLogin();
                    break;
                case R.id.createAccountButton:
                    presenter.createAccount(
                            getApplicationContext(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            dni.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            commission.getText().toString(),
                            group.getText().toString()
                    );
                    break;
            }
        }
    };

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}