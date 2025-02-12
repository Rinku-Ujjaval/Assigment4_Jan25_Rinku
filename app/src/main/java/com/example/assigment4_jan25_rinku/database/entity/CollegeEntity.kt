package com.example.assigment4_jan25_rinku.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CollegeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("name") var name: String,
    @ColumnInfo("Country") var countryName: String,
    @ColumnInfo("webpage") var webpage: String? = null
) : Serializable