package com.example.assigment4_jan25_rinku.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("sId") val sId: Int? = null,
    @ColumnInfo("collegeId") val collegeId: Int? = null,
    @ColumnInfo("name") var name: String?=null,
    @ColumnInfo("dob") var dob: String?=null,
    @ColumnInfo("course") var course: String? = null
) : Serializable


data class StudentAndCollege(
    @Embedded val collegeEntity: CollegeEntity, @Relation(
        parentColumn = "id", entityColumn = "sId"
    ) val studentEntity: List<StudentEntity>
)