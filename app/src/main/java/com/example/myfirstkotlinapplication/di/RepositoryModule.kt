package com.example.myfirstkotlinapplication.di

import com.example.myfirstkotlinapplication.data.entity.source.ApiRepository
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module


val repositoryModule = module {
    factory {
        ApiRepository(get(), Schedulers.io())
    }
}