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
    private var _title =  mutableStateOf("")
    private var _year  = mutableStateOf("")
    private var _content = mutableStateOf("")
    private var _selectedAuthor = mutableIntStateOf(0)
    private var _expanded = mutableStateOf(false)
    private val _createBookRepository = BookRepository(app)

    fun saveBook(){
        if (_title.value.isNotBlank() && _year.value.isNotBlank() && _selectedAuthor.intValue != 0) {
            viewModelScope.launch {
                _createBookRepository.insertBook(
                    BookEntity(
                        title = _title.value,
                        year = _year.value.toInt(),
                        authorId = _selectedAuthor.intValue,
                        content = _content.value
                    )
                )
                _title.value = ""
                _year.value = ""
                _content.value = ""
                _selectedAuthor.intValue = 0
            }
        }
    }

    fun getContentValue() : String {
        return _content.value
    }

    fun setContentValue(value : String) {
        _content.value = value
    }

    fun getTitleValue() : String {
        return _title.value
    }

    fun getYearValue() : String {
        return _year.value
    }

    fun getSelectedAuthorValue() : Int {
        return _selectedAuthor.intValue
    }

    fun getExpandedValue() : Boolean {
        return _expanded.value
    }

    fun setTitleValue(value : String) {
        _title.value = value
    }

    fun setYearValue(value : String) {
        if (value.all { it.isDigit() }) {
            _year.value = value
        }
    }

    fun setSelectedAuthor(value : Int) {
        _selectedAuthor.intValue = value
    }

    fun setExpandedValue(value : Boolean) {
        _expanded.value = value
    }

    val isButtonEnabled = getTitleValue().isNotBlank() && getYearValue().isNotBlank()

}
