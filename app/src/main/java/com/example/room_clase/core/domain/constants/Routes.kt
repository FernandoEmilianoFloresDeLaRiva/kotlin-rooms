package com.example.room_clase.core.domain.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import com.example.room_clase.core.domain.models.NavItem

object Routes {
    val HomeRoute = NavItem("Home", "home", Icons.Outlined.Home)
    val CreateBookRoute = NavItem("Create", "create", Icons.Outlined.Create)
    val CreateAuthorRoute = NavItem("Create Author", "create_author", Icons.Outlined.AddCircle)

    val pages = listOf<NavItem>(HomeRoute, CreateBookRoute, CreateAuthorRoute)
}