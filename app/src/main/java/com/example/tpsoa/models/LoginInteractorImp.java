package com.example.tpsoa.models;


import android.util.Log;
import android.util.Patterns;

import com.example.tpsoa.dtos.requests.LoginRequest;
import com.example.tpsoa.dtos.responses.LoginResponse;
import com.example.tpsoa.services.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractorImp implements LoginInteractor {
    private String uri = "http://so-unlam.net.ar/api/";
    private String env = "PROD";

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

        Call<LoginResponse> call = apInt.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()) {
                    ofs.onFinished(400, "Credenciales inválidas.");
                    Log.i("RETROFIT", "CREDENCIALES INVALIDAS");
                }else {
                    ofs.onFinished(200, response.toString());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("RETROFIT", "ERROR DE CONEXIÓN");
            }
        });

    }
}
