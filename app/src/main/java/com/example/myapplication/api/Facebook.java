package com.example.myapplication.api;


import com.example.myapplication.Model.ApiFacebook;
import com.example.myapplication.Model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Facebook {

    @POST("login")
    Call<ApiFacebook> userLogin(@Body ApiFacebook apiUser);

    @POST("signup")
    Call<Void> registerUser(@Body ApiFacebook users);

    @GET("tasks")
    Call<List<Task>>
    getTasks(@Header("Authorization")
                     String authHeader);
}
