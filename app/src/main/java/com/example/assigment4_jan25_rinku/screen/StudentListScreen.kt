package com.example.assigment4_jan25_rinku.screen

import CustomAlertDialog
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assigment4_jan25_rinku.compose.StudentList
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity
import com.example.assigment4_jan25_rinku.navigation.NavRoute
import com.example.assigment4_jan25_rinku.viewmodel.StudentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    studentViewModel: StudentViewModel,
    collegeID: String?,
    name: String?,
    remNavController: NavHostController
) {
    var isOpenDialog = remember { mutableStateOf(false) }

    var studentEntity = StudentEntity()
    var studentEntityList =
        studentViewModel.getStudentList(collegeID).collectAsState(emptyList()).value

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { remNavController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            title = {
                Text(
                    "$name Student List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, titleContentColor = Color.White
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { remNavController.navigate("${NavRoute.AddStudentScreen.route}/$collegeID") },
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }) { innerPa ->
        Column(
            modifier = Modifier.padding(innerPa),
            verticalArrangement = Arrangement.Center
        ) {
            if (studentEntityList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()  // Display a circular loading indicator
                }
            } else {
                StudentList(
                    studentEntityList,
                    deleteStudent = {
                        isOpenDialog.value = true
                        studentEntity = it
                    },
                )
            }
        }
        if (isOpenDialog.value == true) {
            CustomAlertDialog(
                yes = {
                    isOpenDialog.value = false
                    studentViewModel.deleteStudent(flowOf(studentEntity).flowOn(Dispatchers.IO))
                },
                no = { isOpenDialog.value = false },
                isOpenDialog,
                "Are you sure want remove this student?"
            )
        }
    }
}
