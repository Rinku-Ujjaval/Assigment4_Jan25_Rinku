package com.example.assigment4_jan25_rinku.repositry

import com.example.assigment4_jan25_rinku.database.dao.CollegeDao
import com.example.assigment4_jan25_rinku.database.entity.CollegeEntity
import com.example.assigment4_jan25_rinku.model.CollegeList
import com.example.assigment4_jan25_rinku.services.CollegeServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.toString

class CollegeRepository(
    private val collegeDao: CollegeDao, private val collegeServices: CollegeServices
) {

    fun getCollegeList(): Flow<List<CollegeList>> {
        return collegeServices.getCollegeList()
    }

    fun searchByCountryCollegeList(country: String): Flow<List<CollegeList>> {
        return collegeServices.searchByCountryCollegeList(country).flowOn(Dispatchers.IO)
    }

    fun insertFavCollege(college: Flow<CollegeList>) {
        CoroutineScope(Dispatchers.IO).launch {
            college.collect {
                collegeDao.insertCollegeList(
                    CollegeEntity(
                        name = it.name.toString(),
                        countryName = it.country.toString(),
                        webpage = it.web_pages.toString()
                    )
                )
            }
        }
    }

    fun getFavCollegeDB(): Flow<List<CollegeEntity>> {
        return collegeDao.getAllCollege()
    }

    fun searchFavCollegeDB(search: String): Flow<List<CollegeEntity>> {
        return collegeDao.searchFavCollegeDB(search)
    }
}