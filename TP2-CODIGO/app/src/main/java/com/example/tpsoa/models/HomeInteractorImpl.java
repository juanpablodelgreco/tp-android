package com.example.tpsoa.models;

import android.content.Context;
import android.util.Log;
import com.example.tpsoa.dtos.responses.PublicApiResponse;
import com.example.tpsoa.interfaces.PublicApiInterface;
import com.example.tpsoa.presenters.OnFinishListenerPublic;
import com.example.tpsoa.utils.Connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeInteractorImpl implements HomeInteractor {
    private String uri = "https://www.dolarsi.com/";

    @Override
    public void getData(OnFinishListenerPublic ofs, Context ctx) {
        if(!Connection.checkConnection(ctx)) {
            ofs.showToast("No hay conexión a internet");
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(uri)
                .build();

        PublicApiInterface apInt = retrofit.create(PublicApiInterface.class);

        Call<List<PublicApiResponse>> call = apInt.get();
        call.enqueue(new Callback<List<PublicApiResponse>>() {
            @Override
            public void onResponse(Call<List<PublicApiResponse>> call, Response<List<PublicApiResponse>> response) {
                if(!response.isSuccessful()) {
                    ofs.onFinished(response.code(), "Error al recibir data.");
                    Log.i("ERROR", "Error al recibir data.\"");
                }else {
                    ofs.onFinished(200, response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PublicApiResponse>> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("ERROR", "ERROR DE CONEXIÓN");
            }
        });
    }
}
