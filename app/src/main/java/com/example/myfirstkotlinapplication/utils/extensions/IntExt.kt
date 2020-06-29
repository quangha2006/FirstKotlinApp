package com.example.myfirstkotlinapplication.utils.extensions

fun Int?.isSuccess() = this == 1

fun Int?.isError() = this != 1
