package com.example.room_clase.home.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_clase.core.data.local.authors.relations.AuthorWithBooks
import com.example.room_clase.create_book.domain.repository.BookRepository
import kotlinx.coroutines.launch

class HomePageViewModel(app : Application) : AndroidViewModel(app) {
    private val _authorsWithBooks = mutableStateOf<List<AuthorWithBooks>>(emptyList())
    val authorsWithBooks: State<List<AuthorWithBooks>> = _authorsWithBooks
    private val _bookRepository = BookRepository(app)

    init {
        getBooks()
    }

    fun getBooks(){
        viewModelScope.launch {
            _authorsWithBooks.value = _bookRepository.getBooks()
        }
    }
}