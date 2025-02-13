package com.example.room_clase.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.moviles_223251_proyecto.core.ui.composables.navbar.NavBar
import com.example.room_clase.core.domain.constants.Routes
import com.example.room_clase.create_author.ui.pages.CreateAuthorPage
import com.example.room_clase.create_author.ui.viewmodels.CreateAuthorViewModel
import com.example.room_clase.create_book.ui.pages.CreateBookPage
import com.example.room_clase.create_book.ui.viewmodels.CreateBookViewModel
import com.example.room_clase.home.ui.pages.HomePage
import com.example.room_clase.home.ui.viewmodels.HomePageViewModel

@Composable
fun IndexRouter(
    createBookViewModel: CreateBookViewModel,
    homeVW : HomePageViewModel,
    createAuthorVM : CreateAuthorViewModel,
    modifier: Modifier = Modifier
) {
    val route = rememberSaveable{
        mutableStateOf(Routes.HomeRoute.route)
    }

    Scaffold(
        bottomBar = {
            NavBar(route)
        }
    ){
        Column(modifier = modifier.padding(it)){
            when(route.value){
                Routes.HomeRoute.route -> HomePage(homePageViewModel = homeVW)
                Routes.CreateBookRoute.route -> CreateBookPage(
                    createBookViewModel = createBookViewModel,
                    createAuthorVM = createAuthorVM,
                )
                Routes.CreateAuthorRoute.route -> CreateAuthorPage(
                    createAuthorViewModel = createAuthorVM,
                )
            }
        }
    }
}