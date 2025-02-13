package com.example.room_clase.core.data.local.authors.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.room_clase.core.data.local.authors.entities.AuthorEntity
import com.example.room_clase.core.data.local.authors.relations.AuthorWithBooks

@Dao
interface AuthorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: AuthorEntity)

    @Query("SELECT * FROM authors")
    suspend fun getAllAuthors(): List<AuthorEntity>

    @Query("SELECT * FROM authors WHERE id = :authorId LIMIT 1")
    suspend fun getAuthorById(authorId: Int): AuthorEntity?

    @Transaction
    @Query("SELECT * FROM authors")
    suspend fun getAuthorWithBooks(): List<AuthorWithBooks>
}