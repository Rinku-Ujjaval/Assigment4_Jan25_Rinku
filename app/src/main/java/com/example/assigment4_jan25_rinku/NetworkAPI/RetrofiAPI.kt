package com.example.assigment4_jan25_rinku.NetworkAPI

import com.example.assigment4_jan25_rinku.utils.baseUrl
import com.example.assigment4_jan25_rinku.CollegeInterFace
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RetrofitAPI {

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
    }

    var retrofitbuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .baseUrl("$baseUrl/")
        .build()

    val services = retrofitbuilder.create(CollegeInterFace::class.java)
}