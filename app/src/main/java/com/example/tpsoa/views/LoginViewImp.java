package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tpsoa.R;
import com.example.tpsoa.models.LoginInteractorImp;
import com.example.tpsoa.presenters.LoginPresenter;
import com.example.tpsoa.presenters.LoginPresenterImp;

public class LoginViewImp extends Activity implements LoginView {

    private EditText email;
    private EditText password;
    private TextView errorView;
    private ProgressBar progressBar;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginInputUsername);
        password = findViewById(R.id.loginInputPassword);
        errorView = findViewById(R.id.loginInformationText);
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.loginButton).setOnClickListener(listenerButtons);
        findViewById(R.id.loginCreateAccountButton).setOnClickListener(listenerButtons);
        presenter = new LoginPresenterImp(this, new LoginInteractorImp());
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
        Log.i("Ejecuto", "onResume login Activity");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Ejecuto", "onPause login Activity");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Ejecuto", "onStop login Activity");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
        Log.i("Ejecuto", "onDestroy login Activity");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() { progressBar.setVisibility(View.INVISIBLE);}

    @Override
    public void showErrorMessage(String message) {
        errorView.setText(message);
        errorView.setTextColor(Color.RED);
    }

    @Override
    public void navigateToCreateAccount() {
        Intent intent = new Intent(this, CreateAccountViewImp.class);
        startActivity(intent);
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()){
                case R.id.loginButton:
                    presenter.validateCredentials(email.getText().toString(), password.getText().toString());
                    break;
                case R.id.loginCreateAccountButton:
                    navigateToCreateAccount();
            }

        }
    };
}