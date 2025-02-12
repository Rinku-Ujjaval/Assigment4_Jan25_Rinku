package com.example.assigment4_jan25_rinku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigment4_jan25_rinku.repositry.CollegeRepository
import com.example.assigment4_jan25_rinku.database.entity.CollegeEntity
import com.example.assigment4_jan25_rinku.model.CollegeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retry
import kotlin.toString

class CollegeViewModel(val collegeRepository: CollegeRepository) : ViewModel() {


    fun getCollegeList(): Flow<List<CollegeList>> {
        return collegeRepository.getCollegeList().catch { print(it) }
    }

    fun searchByCountryCollegeList(country: String?): Flow<List<CollegeList>> {
        return collegeRepository.searchByCountryCollegeList(country.toString())
            .catch { print(it) }
    }

    fun insertFavCollegeDB(collegeList: Flow<CollegeList>) {
        collegeRepository.insertFavCollege(collegeList)
    }

    fun getFavCollegeDB(): Flow<List<CollegeEntity>> {
        return collegeRepository.getFavCollegeDB().catch { print(it) }
    }

    fun getSearchFromDB(search: String): Flow<List<CollegeEntity>> {
        return collegeRepository.searchFavCollegeDB(search).catch { print(it) }.retry(
            retries = 1
        ) {
            return@retry true
        }
    }
}