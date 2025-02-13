package com.example.room_clase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.room_clase.core.navigation.IndexRouter
import com.example.room_clase.core.ui.theme.Room_claseTheme
import com.example.room_clase.create_author.ui.viewmodels.CreateAuthorViewModel
import com.example.room_clase.create_book.ui.viewmodels.CreateBookViewModel
import com.example.room_clase.home.ui.viewmodels.HomePageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val createAuthorVM = ViewModelProvider(this).get(CreateAuthorViewModel::class.java)
        val homeVM = ViewModelProvider(this).get(HomePageViewModel::class.java)
        val createBookVM = ViewModelProvider(this).get(CreateBookViewModel::class.java)
        setContent {
            Room_claseTheme {
                IndexRouter(
                    createAuthorVM = createAuthorVM,
                    homeVW = homeVM,
                    createBookViewModel = createBookVM
                )
            }
        }
    }
}