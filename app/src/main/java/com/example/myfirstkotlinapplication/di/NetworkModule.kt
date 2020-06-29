package com.example.myfirstkotlinapplication.di

import com.example.myfirstkotlinapplication.BuildConfig
import com.example.myfirstkotlinapplication.utils.retrofit.NullOnEmptyConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val HOST = "http://qhcloud.ddns.net/"

val networkModule = module {
    single { createInterceptor() }
    single { createOkHttpClient(get()) }
    single { createNetworkClient(get(), HOST) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(NullOnEmptyConverterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

private fun createOkHttpClient(requestInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(log(BuildConfig.DEBUG))
    return builder.build()
}

private fun createInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val builder = original.newBuilder()
        .method(original.method, original.body)

    val newRequest = builder.build()
    chain.proceed(newRequest)
}


private fun log(enabled: Boolean): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (enabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

