package com.example.myfirstkotlinapplication.data.entity.response


import com.google.gson.annotations.SerializedName

data class VersionInfoRes(
    @SerializedName("android")
    val android: ArrayList<Android>? = arrayListOf(),
    @SerializedName("version")
    val version: Int? = 0
) : BaseResponse()