package com.example.tpsoa.services;

import com.example.tpsoa.dtos.requests.CreateUserRequest;
import com.example.tpsoa.dtos.responses.CreateUserResponse;
import com.example.tpsoa.dtos.requests.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/login")
    Call<String>login(@Body LoginRequest request);
    @POST("api/register")
    Call<CreateUserResponse>createAccount(@Body CreateUserRequest request);
}
