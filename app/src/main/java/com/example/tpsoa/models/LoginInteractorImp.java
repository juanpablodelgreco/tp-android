package com.example.tpsoa.models;


import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import com.example.tpsoa.dtos.requests.LoginRequest;
import com.example.tpsoa.dtos.responses.LoginResponse;
import com.example.tpsoa.presenters.OnFinishListener;
import com.example.tpsoa.services.ApiInterface;
import com.example.tpsoa.services.ConnectionService;
import org.json.JSONObject;
import java.io.IOException;
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
                    try {
                        ofs.onFinished(response.code(), "Error al loguearse.");
                        Log.i("LOGIN", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    ofs.onFinished(200, response.toString());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("LOGIN", "ERROR DE CONEXIÓN");
            }
        });

    }
}
