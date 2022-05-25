package com.example.tpsoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Asocio las partes gráficas a las variables.
        username =(EditText) findViewById(R.id.loginInputUsername);
        password =(EditText) findViewById(R.id.loginInputPassword);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnCreateAccount = (Button) findViewById(R.id.loginCreateAccountButton);
        btnLogin.setOnClickListener(listenerButtons);
        btnCreateAccount.setOnClickListener(listenerButtons);
        Log.i("Ejecuto", "onCreate login Activity");
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()){
                case R.id.loginButton:
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                    break;
                case R.id.loginCreateAccountButton:
                    intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Error buttons listener.", Toast.LENGTH_SHORT).show();
            }

        }
    };
}