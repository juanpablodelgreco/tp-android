package com.example.tpsoa.models;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import com.example.tpsoa.dtos.requests.CreateUserRequest;
import com.example.tpsoa.dtos.responses.CreateUserResponse;
import com.example.tpsoa.interfaces.SoaApiInterface;
import com.example.tpsoa.presenters.OnFinishListenerSoa;
import com.example.tpsoa.utils.Connection;
import com.example.tpsoa.utils.SessionInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountInteractorImp implements CreateAccountInteractor {
    private String uri = "http://so-unlam.net.ar/api/";
    private String env = "PROD";

    @Override
    public void createAccount(OnFinishListenerSoa ofs, Context ctx, String firstName, String lastName, String dni, String email, String commission, String password, String group) {

        if(
            email.isEmpty() ||
            password.isEmpty() ||
            dni.isEmpty() ||
            commission.isEmpty() ||
            group.isEmpty() ||
            firstName.isEmpty() ||
            lastName.isEmpty()
        ){
            ofs.showToast("Todos los campos son obligatorios.");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ofs.showToast("Email inválido.");
            return;
        }

        if(password.length() < 8){
            ofs.showToast("La password debe ser mayor o igual a 8 carácteres.");
            return;
        }

        if(!commission.equals("1900") && !commission.equals("3900")){
            ofs.showToast("La comisión debe ser 1900 o 3900.");
            return;
        }

        if(!Connection.checkConnection(ctx)) {
            ofs.showToast("No hay conexión a internet");
            return;
        }

        CreateUserRequest request = new CreateUserRequest(
                firstName,
                lastName,
                dni,
                email,
                password,
                Integer.parseInt(commission),
                Integer.parseInt(group)
        );

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(uri)
                .build();

        SoaApiInterface apInt = retrofit.create(SoaApiInterface.class);

        Call<CreateUserResponse> call = apInt.createAccount(request);
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if(!response.isSuccessful()) {
                    ofs.onFinished(response.code(), "Error al crear el usuario");
                    Log.i("CREATE_USER", "NO SE PUDO CREAR EL USUARIO.");
                }else {
                    ofs.onFinished(200, "Usuario creado.");
                    CreateUserResponse resp = response.body();
                    SessionInfo.setTokens(resp.getToken(), resp.getToken_refresh());
                    RegisterEvent rge = new RegisterEvent( "CREATE_ACCOUNT", "Se registro un usuario.");
                    new Thread(rge).start();
                    Log.i("CREATE_USER", "USUARIO CREADO.");
                }
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                ofs.onFailure(t);
                Log.i("CREATE_USER", "ERROR DE CONEXIÓN");
            }
        });
    }
}
