package com.example.room_clase.home.ui.composables

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.room_clase.core.data.local.authors.relations.AuthorWithBooks
import com.example.room_clase.core.data.local.books.entities.BookEntity
import com.example.room_clase.core.services.TextToSpeechService

@Composable
fun BookCard(authorWithBooks: BookEntity, context : Context) {
    val intent = Intent(context, TextToSpeechService::class.java)

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
                text = "Autor: ${authorWithBooks.title} - (${authorWithBooks.year})",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = authorWithBooks.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )


            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                intent.putExtra("BOOK_TEXT", "Titulo ${authorWithBooks.title}, contenido ${authorWithBooks.content}")
                context.startService(intent)
            }) {
                Text("Escuchar")
            }
        }
    }
}