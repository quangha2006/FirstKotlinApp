package com.example.myfirstkotlinapplication.di

import com.example.myfirstkotlinapplication.data.entity.source.remote.AppService
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {

    factory {
        createAppService(get())
    }
}

private fun createAppService(retrofit: Retrofit) = retrofit.create(AppService::class.java)