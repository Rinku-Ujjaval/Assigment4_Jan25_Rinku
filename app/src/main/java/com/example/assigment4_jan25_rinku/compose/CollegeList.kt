package com.example.assigment4_jan25_rinku.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assigment4_jan25_rinku.model.CollegeList
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun CollegeList(collegeList: List<CollegeList>, onFavClick: (CollegeList) -> Unit,webView: (CollegeList) -> Unit) {
    LazyColumn {
        items(collegeList) {
            Card(modifier = Modifier.padding(10.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            it.name.toString(),
                            modifier = Modifier.fillMaxSize(0.7f),
                            fontSize = 18.sp,
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Icon(
                                Icons.Default.ExitToApp,
                                contentDescription = "Move to web",
                                modifier = Modifier.clickable(onClick = {
                                    webView(it)
                                },)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Add to Fav",
                                modifier = Modifier.clickable(onClick = { onFavClick(it) })
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(it.country.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}