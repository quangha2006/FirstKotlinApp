package com.example.myfirstkotlinapplication
import retrofit2.Call
import retrofit2.http.GET

public interface RequestInterface {
    @GET("api/androidversion")
    fun getJSON(): Call<JSONResponse>
}