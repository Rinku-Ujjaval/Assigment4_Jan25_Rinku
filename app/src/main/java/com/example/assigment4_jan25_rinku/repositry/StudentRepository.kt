package com.example.assigment4_jan25_rinku.repositry

import com.example.assigment4_jan25_rinku.database.dao.StudentDao
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentRepository(private val studentDao: StudentDao) {

    fun getStudentList(): Flow<List<StudentEntity>> {
        return studentDao.getStudent()
    }

    fun insertStudent(studentEntity: Flow<StudentEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            studentEntity.collect {
                studentDao.insertStudent(
                    StudentEntity(
                        name = it.name,
                        collegeId = it.collegeId,
                        course = it.course,
                        dob = it.dob
                    )
                )

            }
        }
    }

    fun deleteStudent(studentEntity: Flow<StudentEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            studentEntity.collect {
                studentDao.deleteStudent(it)
            }
        }
    }
}