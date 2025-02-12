package com.example.assigment4_jan25_rinku

import com.example.assigment4_jan25_rinku.model.CollegeList
import retrofit2.http.GET
import retrofit2.http.Query

interface CollegeInterFace {

    @GET("search?")
    suspend fun searchByCountryCollegeList(@Query("country") country: String): List<CollegeList>

    @GET("search")
    suspend fun getCollegeList(): List<CollegeList>
}