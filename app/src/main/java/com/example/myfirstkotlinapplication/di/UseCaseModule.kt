package com.example.myfirstkotlinapplication.di

import com.example.myfirstkotlinapplication.domain.MainUseCase
import com.example.myfirstkotlinapplication.domain.MainUserCaseIml
import org.koin.dsl.module

val useCaseModule = module {
    factory<MainUseCase> {
        MainUserCaseIml(get())
    }
}