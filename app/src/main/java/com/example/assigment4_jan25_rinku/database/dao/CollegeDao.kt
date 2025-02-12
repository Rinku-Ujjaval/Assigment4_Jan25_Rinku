package com.example.assigment4_jan25_rinku.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.assigment4_jan25_rinku.database.entity.CollegeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollegeDao {

    @Insert
    fun insertCollegeList(collegeEntity: CollegeEntity)

    @Delete
    suspend fun deleteCollege(collegeEntity: CollegeEntity)

    @Query("select * from collegeentity")
    fun getAllCollege(): Flow<List<CollegeEntity>>

    @Query("select * from collegeentity where Country = :search")
    fun searchFavCollegeDB(search: String): Flow<List<CollegeEntity>>
}