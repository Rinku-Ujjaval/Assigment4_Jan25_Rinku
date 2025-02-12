package com.example.assigment4_jan25_rinku.services

import androidx.lifecycle.LiveData
import com.example.assigment4_jan25_rinku.NetworkAPI.RetrofitAPI
import com.example.assigment4_jan25_rinku.model.CollegeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CollegeServices {

    fun getCollegeList(): Flow<List<CollegeList>> = flow {
        emit(RetrofitAPI().services.getCollegeList())
    }


     fun searchByCountryCollegeList(country: String) = flow {
        emit(RetrofitAPI().services.
        searchByCountryCollegeList(country))
    }
}