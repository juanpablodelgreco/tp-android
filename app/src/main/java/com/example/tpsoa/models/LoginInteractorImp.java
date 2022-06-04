package com.example.tpsoa.models;


import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.tpsoa.dtos.LoginRequest;
import com.example.tpsoa.presenters.LoginPresenter;
import com.example.tpsoa.presenters.LoginPresenterImp;
import com.example.tpsoa.services.ApiInterface;
import com.example.tpsoa.views.LoginActivity;
import com.example.tpsoa.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractorImp implements LoginInteractor {
    private String uri = "http://so-unlam.net.ar/api/";
    private String env = "TEST";

    @Override
    public void login(final OnFinishListener ofs, String email, String password) {
        LoginRequest request = new LoginRequest();
        request.setEnv(env);
        request.setEmail(email);
        request.setPassword(password);

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ofs.onValidationFieldFail("Email inválido.");
            return;
        }

        if(password.length() < 8){
            ofs.onValidationFieldFail("El password debe ser mayor o igual a 8 carácteres.");
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(uri)
                .build();

        ApiInterface apInt = retrofit.create(ApiInterface.class);

        Call<String> call = apInt.login(request);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()) {
                    ofs.onFinished(response.code(), "Credenciales inválidas.");
                    Log.i("RETROFIT", "CREDENCIALES INVALIDAS");
                }else {
                    Log.i("RETROFIT", "LOGIN EXITOSO");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("RETROFIT", "ERROR DE CONEXIÓN");
            }
        });

    }
}
