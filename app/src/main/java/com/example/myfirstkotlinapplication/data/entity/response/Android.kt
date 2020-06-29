package com.example.myfirstkotlinapplication.data.entity.response


import com.google.gson.annotations.SerializedName

data class Android(
    @SerializedName("api")
    val api: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("releasedate")
    val releasedate: String? = "",
    @SerializedName("ver")
    val ver: String? = ""
)