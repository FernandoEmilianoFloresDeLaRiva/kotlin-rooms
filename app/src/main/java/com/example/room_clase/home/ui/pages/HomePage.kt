package com.example.room_clase.home.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.room_clase.core.data.local.authors.relations.AuthorWithBooks
import com.example.room_clase.home.ui.viewmodels.HomePageViewModel

@Composable
fun HomePage(
    homePageViewModel: HomePageViewModel
) {
    LaunchedEffect(Unit){
        homePageViewModel.getBooks()
    }

    val authorsWithBooks = homePageViewModel.authorsWithBooks.value

    LazyColumn {
        items(authorsWithBooks.size) {
            BookCard(authorsWithBooks[it])
        }
    }
}

@Composable
fun BookCard(authorWithBooks: AuthorWithBooks) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Autor: ${authorWithBooks.author.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            authorWithBooks.books.forEach { book ->
                Text(
                    text = "- ${book.title} (${book.year})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}