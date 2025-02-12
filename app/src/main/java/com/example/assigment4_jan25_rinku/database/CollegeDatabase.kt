package com.example.assigment4_jan25_rinku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assigment4_jan25_rinku.database.dao.CollegeDao
import com.example.assigment4_jan25_rinku.database.dao.StudentDao
import com.example.assigment4_jan25_rinku.database.entity.CollegeEntity
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity

@Database(entities = [CollegeEntity::class, StudentEntity::class], version = 2)
abstract class CollegeDatabase() : RoomDatabase() {

    abstract fun collegeDao(): CollegeDao

    abstract fun studentDao(): StudentDao

    companion object {
        private var collegeDatabase: CollegeDatabase? = null
        fun getDatabase(context: Context): CollegeDatabase {
            synchronized(this) {
                collegeDatabase = Room.databaseBuilder(
                    context, CollegeDatabase::class.java, "world_college.db"
                ).build()
            }
            return collegeDatabase!!
        }
    }

}