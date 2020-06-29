package com.example.myfirstkotlinapplication.domain

import com.example.myfirstkotlinapplication.data.entity.response.VersionInfoRes
import com.example.myfirstkotlinapplication.data.entity.source.ApiRepository
import io.reactivex.Single

interface MainUseCase {
    fun getVersionInfo(): Single<VersionInfoRes>
}

class MainUserCaseIml(private val apiRepository: ApiRepository) : MainUseCase {
    override fun getVersionInfo(): Single<VersionInfoRes> {
        return apiRepository.getVersionInfo()
    }
}