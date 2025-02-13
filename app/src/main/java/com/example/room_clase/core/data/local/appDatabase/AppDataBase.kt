package com.example.room_clase.core.data.local.appDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room_clase.core.data.local.authors.dao.AuthorDAO
import com.example.room_clase.core.data.local.authors.entities.AuthorEntity
import com.example.room_clase.core.data.local.books.dao.BookDAO
import com.example.room_clase.core.data.local.books.entities.BookEntity

@Database(entities = [AuthorEntity::class,
    BookEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun authorDao(): AuthorDAO

    abstract fun bookDao(): BookDAO

}