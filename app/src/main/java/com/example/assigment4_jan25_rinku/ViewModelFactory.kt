package com.example.assigment4_jan25_rinku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.assigment4_jan25_rinku.repositry.CollegeRepository
import com.example.assigment4_jan25_rinku.repositry.StudentRepository
import com.example.assigment4_jan25_rinku.viewmodel.CollegeViewModel
import com.example.assigment4_jan25_rinku.viewmodel.StudentViewModel

class ViewModelFactory(val collegeRepository: CollegeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CollegeViewModel::class.java)) {
            return CollegeViewModel(collegeRepository) as T
        }
        return super.create(modelClass, extras)
    }
}

class ViewModelStudentFactory(val studentRepository: StudentRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(studentRepository) as T
        }
        return super.create(modelClass, extras)
    }
}