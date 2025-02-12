package com.example.assigment4_jan25_rinku.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity
import com.example.assigment4_jan25_rinku.navigation.NavRoute
import com.example.assigment4_jan25_rinku.viewmodel.StudentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewStudentUI(
    remNavController: NavHostController,
    collegeId: String?,
    studentViewModel: StudentViewModel
) {
    val textName = remember { mutableStateOf("") }
    val isName = remember { mutableStateOf(false) }
    val textDob = remember { mutableStateOf("") }
    val isDOB = remember { mutableStateOf(false) }
    val textCourse = remember { mutableStateOf("") }
    val isCourse = remember { mutableStateOf(false) }
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
                    "Add New Student",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, titleContentColor = Color.White
            ),
        )
    }) { innerPa ->
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(innerPa)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                TextField(
                    value = textName.value,
                    onValueChange = { textName.value = it },
                    modifier = Modifier,
                    enabled = true,
                    label = { Text(text = "Name") },
                    placeholder = { Text("Enter Name") },
                    singleLine = true,
                    isError = !isName.value,
                    maxLines = 1
                )
                if (isName.value) {
                    Text("Please enter name")
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = textDob.value,
                    onValueChange = { textDob.value = it },
                    modifier = Modifier,
                    enabled = true,
                    label = { Text(text = "Date of Birth") },
                    placeholder = { Text("Enter Date of Birth") },
                    singleLine = true,
                    isError = !isDOB.value,
                    maxLines = 1
                )
                if (isDOB.value) {
                    Text("Please enter Date of Birth")
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = textCourse.value,
                    onValueChange = { textCourse.value = it },
                    modifier = Modifier,
                    enabled = true,
                    label = { Text(text = "Course") },
                    placeholder = { Text("Enter Course") },
                    singleLine = true,
                    isError = !isCourse.value,
                    maxLines = 2
                )
                if (isCourse.value) {
                    Text("Please enter Course")
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
            Button(onClick = {
                if (textName.value.isEmpty()) {
                    isName.value = true
                } else if (textDob.value.isEmpty()) {
                    isDOB.value = true
                } else if (textCourse.value.isEmpty()) {
                    isCourse.value = true
                } else {
                    studentViewModel.insertStudent(
                        flowOf(
                            StudentEntity(
                                collegeId = collegeId?.toInt(),
                                name = textName.value,
                                dob = textDob.value,
                                course = textCourse.value
                            )
                        ).flowOn(Dispatchers.IO)
                    )
                    Toast.makeText(
                        context, "Save Successfully!",
                        Toast.LENGTH_LONG
                    ).show();
                    remNavController.navigateUp()
                }
            }) {
                Text("Save Student")
            }
        }
    }
}