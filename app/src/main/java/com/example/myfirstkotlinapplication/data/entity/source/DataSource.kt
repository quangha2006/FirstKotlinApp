package com.example.myfirstkotlinapplication.data.entity.source

import com.example.myfirstkotlinapplication.data.entity.response.VersionInfoRes
import io.reactivex.Single

interface DataSource {
    fun getVersionInfo(): Single<VersionInfoRes>
}