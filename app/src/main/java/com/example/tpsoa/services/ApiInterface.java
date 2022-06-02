package com.example.tpsoa.services;

import com.example.tpsoa.dtos.CreateUserRequest;
import com.example.tpsoa.dtos.CreateUserResponse;
import com.example.tpsoa.dtos.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/login")
    Call<String>login(@Body LoginRequest request);
    @POST("api/register")
    Call<CreateUserResponse>createUser(@Body CreateUserRequest request);
}
