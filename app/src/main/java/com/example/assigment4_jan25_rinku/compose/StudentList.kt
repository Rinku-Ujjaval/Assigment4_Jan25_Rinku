package com.example.assigment4_jan25_rinku.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.assigment4_jan25_rinku.database.entity.StudentAndCollege
import com.example.assigment4_jan25_rinku.database.entity.StudentEntity

@Composable
fun StudentList(
    studentEntity: List<StudentEntity>,
    deleteStudent: (StudentEntity) -> Unit
) {
    LazyColumn {
        items(studentEntity) {
            Card(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            "Name: ${it.name}",
                            fontSize = 20.sp,
                            maxLines = 3,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            "Course : ${it.course.toString()}",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            "DOB: ${it.dob}",
                            fontSize = 18.sp,
                            maxLines = 3,
                            color = Color.Black
                        )
                    }
                    Icon(
                        Icons.Default.Delete,
                        tint = Color.Black,
                        contentDescription = "Delete Student",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(
                                onClick = {
                                    deleteStudent(it)
                                },
                            )
                    )
                }
            }
        }
    }
}