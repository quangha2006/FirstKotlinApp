package com.example.myfirstkotlinapplication
import retrofit2.Call
import retrofit2.http.GET

public interface RequestInterface {
    @GET("android/jsonandroid")
    fun getJSON(): Call<JSONResponse>
}