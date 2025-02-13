package com.example.room_clase.create_book.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_clase.core.data.local.books.entities.BookEntity
import com.example.room_clase.create_book.domain.repository.BookRepository
import kotlinx.coroutines.launch

class CreateBookViewModel(app : Application) : AndroidViewModel(app) {
    var title =  mutableStateOf("")
    var year  = mutableStateOf("")
    var selectedAuthor = mutableIntStateOf(0)
    var expanded = mutableStateOf(false)
    private val _createBookRepository = BookRepository(app)

    fun saveBook(){
        if (title.value.isNotBlank() && year.value.isNotBlank() && selectedAuthor.value != 0) {
            viewModelScope.launch {
                _createBookRepository.insertBook(
                    BookEntity(
                        title = title.value, year = year.value.toInt(), authorId = selectedAuthor.intValue
                    )
                )
                title.value = ""
                year.value = ""
                selectedAuthor.intValue = 0
            }
        }
    }

}
