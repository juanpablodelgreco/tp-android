package com.example.tpsoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tpsoa.view.LoginActivity;

public class CreateAccountActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText dni;
    private EditText email;
    private EditText comission;
    private EditText password;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstName =(EditText) findViewById(R.id.createAccountInputFirstName);
        lastName =(EditText) findViewById(R.id.createAccountInputLastName);
        dni =(EditText) findViewById(R.id.createAccountInputDNI);
        email =(EditText) findViewById(R.id.createAccountInputEmail);
        comission =(EditText) findViewById(R.id.createAccountInputCommission);
        password =(EditText) findViewById(R.id.loginInputPassword);
        btnCreateAccount = (Button) findViewById(R.id.createAccountButton);
        btnCreateAccount.setOnClickListener(listenerButtons);
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
        Log.i("Ejecuto", "onResume createAccount Activity");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Ejecuto", "onPause createAccount Activity");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Ejecuto", "onStop createAccount Activity");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Ejecuto", "onDestroy createAccount Activity");
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){
        Intent intent;
        @Override
        public void onClick(View v) {
            intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };
}