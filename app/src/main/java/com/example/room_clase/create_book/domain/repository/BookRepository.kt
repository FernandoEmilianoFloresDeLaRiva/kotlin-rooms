package com.example.room_clase.create_book.domain.repository

import android.content.Context
import com.example.room_clase.core.data.local.appDatabase.DatabaseProvider
import com.example.room_clase.core.data.local.authors.relations.AuthorWithBooks
import com.example.room_clase.core.data.local.books.entities.BookEntity

class BookRepository(ctx : Context) {
    private val authorDAO = DatabaseProvider.getAppDataBase(ctx).authorDao()
    private val bookDAO = DatabaseProvider.getAppDataBase(ctx).bookDao()

    suspend fun insertBook(book : BookEntity) {
        bookDAO.insertBook(book)
    }

    suspend fun getBooksWithAuthor() : List<AuthorWithBooks> {
        return authorDAO.getAuthorWithBooks()
    }

    suspend fun getBooks() : List<BookEntity> {
        return bookDAO.getAllBooks()
    }
}