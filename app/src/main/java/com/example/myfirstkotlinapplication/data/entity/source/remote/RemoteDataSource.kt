package com.example.myfirstkotlinapplication.data.entity.source.remote

import com.example.myfirstkotlinapplication.data.entity.response.VersionInfoRes
import com.example.myfirstkotlinapplication.data.entity.source.DataSource
import io.reactivex.Single

class RemoteDataSource(private val appService: AppService) :
    DataSource {
    override fun getVersionInfo(): Single<VersionInfoRes> {
        return appService.getVersionInfo()
    }
}