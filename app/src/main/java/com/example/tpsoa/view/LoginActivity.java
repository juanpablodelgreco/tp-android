package com.example.tpsoa.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.tpsoa.CreateAccountActivity;
import com.example.tpsoa.HomeActivity;
import com.example.tpsoa.R;
import com.example.tpsoa.model.LoginInteractorImp;
import com.example.tpsoa.presenter.LoginPresenter;
import com.example.tpsoa.presenter.LoginPresenterImp;

public class LoginActivity extends Activity implements LoginView {

    private EditText username;
    private EditText password;
    private ProgressBar progressBar;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.loginInputUsername);
        password = findViewById(R.id.loginInputPassword);
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
        presenter.onDestroy();
        super.onDestroy();
        Log.i("Ejecuto", "onDestroy login Activity");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("Invalid username.");
    }

    @Override
    public void setPasswordError() {
        password.setError("Invalid password.");
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateAccount() {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()){
                case R.id.loginButton:
                    presenter.validateCredentials(username.getText().toString(), password.getText().toString());
                    break;
                case R.id.loginCreateAccountButton:
                    navigateToCreateAccount();
            }

        }
    };
}