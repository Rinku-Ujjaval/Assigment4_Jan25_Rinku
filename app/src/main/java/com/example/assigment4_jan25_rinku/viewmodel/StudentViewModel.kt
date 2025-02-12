package com.example.assigment4_jan25_rinku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity
import com.example.assigment4_jan25_rinku.repositry.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class StudentViewModel(val studentRepository: StudentRepository) : ViewModel() {

    fun getStudentList(collegeID: String?): Flow<List<StudentEntity>> {
        return studentRepository.getStudentList()
            .map { it.filter { it.collegeId == collegeID!!.toInt() } }
    }

    fun insertStudent(studentEntity: Flow<StudentEntity>) {
        studentRepository.insertStudent(studentEntity)
    }

    fun deleteStudent(studentEntity: Flow<StudentEntity>){
        studentRepository.deleteStudent(studentEntity)
    }
}
