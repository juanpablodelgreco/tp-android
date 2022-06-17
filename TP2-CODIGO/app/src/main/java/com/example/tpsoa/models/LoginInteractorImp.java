package com.example.tpsoa.models;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import com.example.tpsoa.dtos.requests.LoginRequest;
import com.example.tpsoa.dtos.responses.LoginResponse;
import com.example.tpsoa.presenters.OnFinishListener;
import com.example.tpsoa.services.ApiInterface;
import com.example.tpsoa.services.ConnectionService;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractorImp implements LoginInteractor {
    private String uri = "http://so-unlam.net.ar/api/";
    private String env = "PROD";

    @Override
    public void login(final OnFinishListener ofs, Context ctx, String email, String password) {

        if(email.length() == 0 || password.length() == 0){
            ofs.showToast("Ambos campos son obligatorios.");
            return;
        }

        LoginRequest request = new LoginRequest();
        request.setEnv(env);
        request.setEmail(email);
        request.setPassword(password);

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ofs.showToast("Email inválido.");
            return;
        }

        if(password.length() < 8){
            ofs.showToast("El password debe ser mayor o igual a 8 carácteres.");
            return;
        }

        if(!ConnectionService.checkConnection(ctx)) {
            ofs.showToast("No hay conexión a internet");
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
                    JSONObject jObjError = null;
                    ofs.onFinished(response.code(), "Error al loguearse.");
                    Log.i("LOGIN", "Error al loguearse.\"");
                }else {
                    ofs.onFinished(200, response.toString());
                    registerActivity(ctx, email);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("LOGIN", "ERROR DE CONEXIÓN");
            }
        });

    }

    @Override
    public void registerActivity(Context ctx, String username) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String dateTime = simpleDateFormat.format(new Date());

        SharedPreferences sp = ctx.getSharedPreferences("log", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(username, dateTime);
        editor.commit();
    }
}
