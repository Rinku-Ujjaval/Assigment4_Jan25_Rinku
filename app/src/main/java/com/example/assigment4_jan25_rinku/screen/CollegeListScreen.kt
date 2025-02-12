package com.example.assigment4_jan25_rinku.screen

import CustomAlertDialog
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.assigment4_jan25_rinku.compose.CollegeList
import com.example.assigment4_jan25_rinku.compose.SearchUI
import com.example.assigment4_jan25_rinku.model.CollegeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import java.net.URLEncoder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollegeListScreen(collegeViewModel: CollegeViewModel, remNavController: NavHostController) {
    var text = remember { mutableStateOf("") }
    var isOpenDialog = remember { mutableStateOf(false) }
    var collegeList = emptyList<CollegeList>()
    var collegeListItem = CollegeList()
    collegeList = if (text.value.isEmpty()) {
        collegeViewModel.getCollegeList().collectAsState(emptyList()).value
    } else {
        collegeViewModel.searchByCountryCollegeList(text.value.toString())
            .collectAsState(emptyList()).value
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "World College List",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black, titleContentColor = Color.White
        ), actions = {
            Icon(Icons.Default.Favorite,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.clickable() {
                    remNavController.navigate(NavRoute.FavListScreen.route)

                })
        })
    }) { innerPa ->
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            SearchUI(search = { searchText ->
                text.value = searchText
            }, innerPa) {
                CollegeList(collegeList.toList(),
                    onFavClick = { collegeViewModel.insertFavCollegeDB(flowOf(it).flowOn(Dispatchers.IO)) },
                    webView = {
                        remNavController.navigate(
                            "${NavRoute.WebScreen.route}/${
                                it.web_pages?.get(
                                    0
                                ).toString()
                            }"
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
                CollegeList(collegeList.toList(),
                    onFavClick = {
                        isOpenDialog.value = true
                        collegeListItem = it
                    },
                    webView = {
                        val encodedUrl = URLEncoder.encode(it.web_pages?.get(0).toString())
                        remNavController.navigate(
                            "${NavRoute.WebScreen.route}/${encodedUrl}"
                        )
                    })
            }
        }
        if (isOpenDialog.value == true) {
            CustomAlertDialog(
                yes = {
                    isOpenDialog.value = false
                    collegeViewModel.insertFavCollegeDB(flowOf(collegeListItem).flowOn(Dispatchers.IO))
                },
                no = { isOpenDialog.value = false },
                isOpenDialog,
                "Do you want to add college in Favourite List?"
            )
        }
    }


}
