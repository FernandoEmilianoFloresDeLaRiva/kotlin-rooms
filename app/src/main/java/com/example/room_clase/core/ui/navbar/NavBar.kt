package com.example.moviles_223251_proyecto.core.ui.composables.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviles_223251_proyecto.core.ui.composables.navbar.composables.itembar.ItemBar
import com.example.room_clase.core.domain.constants.Routes

@Composable
fun NavBar(route : MutableState<String>) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(12.dp)
            .fillMaxWidth()
    ){
        for(page in Routes.pages) {
            ItemBar(
                page,
                selected = route.value == page.route,
                modifier = Modifier.clickable {
                    route.value = page.route
                }
            )
        }
    }
}