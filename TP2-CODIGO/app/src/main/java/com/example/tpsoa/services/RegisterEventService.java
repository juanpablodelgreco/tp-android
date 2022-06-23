package com.example.tpsoa.services;

import android.util.Log;

import com.example.tpsoa.dtos.requests.RegisterEventRequest;
import com.example.tpsoa.dtos.responses.RegisterEventResponse;
import com.example.tpsoa.utils.SessionInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterEventService {

    public static void register(String type, String msg) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + SessionInfo.authToken);
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("http://so-unlam.net.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SoaApiInterface rfApi = rf.create(SoaApiInterface.class);
        RegisterEventRequest er = new RegisterEventRequest(type, msg);
        Call<RegisterEventResponse> call = rfApi.registerEvent(headers, er);
        call.enqueue(new Callback<RegisterEventResponse>() {
            @Override
            public void onResponse(Call<RegisterEventResponse> call, Response<RegisterEventResponse> response) {
                Log.i("Event", "Evento registrado con éxito.");
            }

            @Override
            public void onFailure(Call<RegisterEventResponse> call, Throwable t) {
                Log.i("Event", "Error al registrar evento.");
            }
        });
    }
}
