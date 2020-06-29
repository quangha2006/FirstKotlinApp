package com.example.myfirstkotlinapplication.data.entity.source

import com.example.myfirstkotlinapplication.data.entity.response.VersionInfoRes
import com.example.myfirstkotlinapplication.data.entity.source.remote.RemoteDataSource
import io.reactivex.Scheduler
import io.reactivex.Single

class ApiRepository(
    private val remoteDataSource: RemoteDataSource,
    private val scheduler: Scheduler
) : DataSource {
    override fun getVersionInfo(): Single<VersionInfoRes> {
        return remoteDataSource.getVersionInfo().subscribeOn(scheduler)
    }
}