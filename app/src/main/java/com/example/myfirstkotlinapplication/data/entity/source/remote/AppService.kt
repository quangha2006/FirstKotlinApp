package com.example.myfirstkotlinapplication.data.entity.source.remote

import com.example.myfirstkotlinapplication.data.entity.response.VersionInfoRes
import io.reactivex.Single
import retrofit2.http.GET

interface AppService {
    @GET("api/androidversion")
    fun getVersionInfo(): Single<VersionInfoRes>
}