package com.example.tpsoa.services;

import com.example.tpsoa.dtos.responses.PublicApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface PublicApiInterface {
    @POST("/api/api.php?type=valoresprincipales")
    Call<List<PublicApiResponse>> get();
}
