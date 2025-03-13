package com.example.room_clase.home.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.room_clase.home.ui.composables.BookCard
import com.example.room_clase.home.ui.viewmodels.HomePageViewModel

@Composable
fun HomePage(
    homePageViewModel: HomePageViewModel
) {
    val context = LocalContext.current
    LaunchedEffect(Unit){
        homePageViewModel.getBooks()
    }

    val authorsWithBooks = homePageViewModel.authorsWithBooks.value

    LazyColumn {
        items(authorsWithBooks.size) {
            BookCard(authorsWithBooks[it], context)
        }
    }
}
