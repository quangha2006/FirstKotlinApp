package com.example.myfirstkotlinapplication

class JSONResponse {

    private val version: Int? = null
    private val android: ArrayList<AndroidVersion>? = null

    fun getFileVersion(): Int? {
        return version
    }

    fun getAndroid(): ArrayList<AndroidVersion>? {
        return android
    }

}