package com.example.room_clase.core.data.local.books.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room_clase.core.data.local.books.entities.BookEntity

@Dao
interface BookDAO {

    @Insert
    suspend fun insertBook(book: BookEntity)

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: Int): BookEntity?

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>

}