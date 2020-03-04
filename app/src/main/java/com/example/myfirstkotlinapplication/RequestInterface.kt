package com.example.myfirstkotlinapplication
import android.telecom.Call
//import retrofit2.Call;
//import retrofit2.http.GET;
import jdk.nashorn.internal.runtime.PropertyDescriptor.GET

public interface RequestInterface {
    @GET("android/jsonandroid")
    fun getJSON(): Call<JSONResponse>
}