package com.example.tpsoa.interfaces;

import com.example.tpsoa.dtos.requests.CreateUserRequest;
import com.example.tpsoa.dtos.requests.RegisterEventRequest;
import com.example.tpsoa.dtos.responses.CreateUserResponse;
import com.example.tpsoa.dtos.requests.LoginRequest;
import com.example.tpsoa.dtos.responses.LoginResponse;
import com.example.tpsoa.dtos.responses.RegisterEventResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface SoaApiInterface {

    @POST("api/login")
    Call<LoginResponse>login(@Body LoginRequest request);
    @POST("api/register")
    Call<CreateUserResponse>createAccount(@Body CreateUserRequest request);
    @POST("api/event")
    Call<RegisterEventResponse>registerEvent(@HeaderMap Map<String, String> headers, @Body RegisterEventRequest request);
}
