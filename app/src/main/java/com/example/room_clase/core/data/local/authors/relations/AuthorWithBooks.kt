package com.example.room_clase.core.data.local.authors.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.room_clase.core.data.local.authors.entities.AuthorEntity
import com.example.room_clase.core.data.local.books.entities.BookEntity

data class AuthorWithBooks(
    @Embedded val author: AuthorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "author_id"
    )
    val books: List<BookEntity>
)
