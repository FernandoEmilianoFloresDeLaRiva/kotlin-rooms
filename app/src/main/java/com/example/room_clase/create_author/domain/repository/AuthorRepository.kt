package com.example.room_clase.create_author.domain.repository

import android.content.Context
import com.example.room_clase.core.data.local.appDatabase.DatabaseProvider
import com.example.room_clase.core.data.local.authors.entities.AuthorEntity

class AuthorRepository(context: Context) {
    private val authorDao = DatabaseProvider.getAppDataBase(context).authorDao()

    suspend fun insertAuthor(author: AuthorEntity) {
        authorDao.insertAuthor(author)
    }

    suspend fun getAuthors(): List<AuthorEntity> {
        return authorDao.getAllAuthors()
    }
}