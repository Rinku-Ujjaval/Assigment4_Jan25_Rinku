@file:Suppress("DEPRECATION")

package com.example.assigment4_jan25_rinku.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.assigment4_jan25_rinku.viewmodel.CollegeViewModel
import com.example.assigment4_jan25_rinku.navigation.NavRoute
import com.example.assigment4_jan25_rinku.compose.FavCollegeList
import com.example.assigment4_jan25_rinku.compose.SearchUI
import com.example.assigment4_jan25_rinku.database.entity.CollegeEntity
import java.net.URLEncoder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavCollegeListScreen(collegeViewModel: CollegeViewModel, remNavController: NavHostController) {

    var text = remember { mutableStateOf("") }
    var collegeList = emptyList<CollegeEntity>()
    collegeList = if (text.value.isEmpty()) {
        collegeViewModel.getFavCollegeDB().collectAsState(emptyList()).value
    } else {
        collegeViewModel.getSearchFromDB(text.value.toString())
            .collectAsState(emptyList()).value
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Favourite College List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { remNavController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }) { innerPa ->
        Column {
            SearchUI(search = { searchText ->
                text.value = searchText
            }, innerPa) {
                FavCollegeList(collegeList.toList(), {
                    val encodedUrl = URLEncoder.encode(it.webpage.toString())
                    remNavController.navigate(
                        "${NavRoute.WebScreen.route}/${encodedUrl}"
                    )
                }, { id, name ->
                    remNavController.navigate(
                        "${NavRoute.StudentScreen.route}/${id}/${name}"
                    )
                })
            }
            if (collegeList.isEmpty()) {
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
                FavCollegeList(collegeList.toList(), {
                    val encodedUrl = URLEncoder.encode(it.webpage.toString())
                    remNavController.navigate(
                        "${NavRoute.WebScreen.route}/${encodedUrl}"
                    )
                }, onClick = { id, name ->
                    remNavController.navigate(
                        "${NavRoute.StudentScreen.route}/${id}/${name}"
                    )
                })
            }
        }
    }
}


