package com.example.assigment4_jan25_rinku.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUI(search: (String) -> Unit, innerPa: PaddingValues, function: @Composable () -> Unit) {
    var searchText = remember { mutableStateOf("") }
    var isSearch = remember { mutableStateOf<Boolean>(false) }
    SearchBar(query = searchText.value,
        onQueryChange = {
            searchText.value = it
            search(searchText.value)
        },
        onSearch = { isSearch.value = true },
        active = isSearch.value,
        onActiveChange = { isSearch.value },
        modifier = Modifier
            .padding(innerPa)
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        placeholder = { Text(text = "Search country wise college list") },
        leadingIcon = {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable(onClick = {
                    isSearch.value = false
                    searchText.value = ""
                })
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier.clickable(onClick = {
                    isSearch.value = true
                })
            )
        }) {
        function()
    }
}