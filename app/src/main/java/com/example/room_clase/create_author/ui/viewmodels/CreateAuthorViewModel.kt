package com.example.room_clase.create_author.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_clase.core.data.local.authors.entities.AuthorEntity
import com.example.room_clase.create_author.domain.repository.AuthorRepository
import kotlinx.coroutines.launch

class CreateAuthorViewModel(app : Application) : AndroidViewModel(app) {
    val name = mutableStateOf("")
    private val _authorRespository = AuthorRepository(app)
    val authors = mutableStateOf(listOf<AuthorEntity>())

    init {
        getAuthors()
    }

    fun saveAuthor(){
        if (name.value.isNotBlank()) {
            viewModelScope.launch {
                _authorRespository.insertAuthor(AuthorEntity(name = name.value))
                name.value = ""
            }
        }
    }

    fun getAuthors(){
        viewModelScope.launch {
            authors.value = _authorRespository.getAuthors()
        }
    }

    fun findAuthorById(id : Int) : AuthorEntity? {
        return authors.value.find { it.id == id }
    }
}