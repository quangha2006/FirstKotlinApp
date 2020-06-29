package com.example.myfirstkotlinapplication.di

import com.example.myfirstkotlinapplication.data.entity.source.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory {
        RemoteDataSource(get())
    }
}