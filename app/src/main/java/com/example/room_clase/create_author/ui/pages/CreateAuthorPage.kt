package com.example.room_clase.create_author.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.room_clase.create_author.ui.viewmodels.CreateAuthorViewModel

@Composable
fun CreateAuthorPage(
    modifier: Modifier = Modifier,
    createAuthorViewModel: CreateAuthorViewModel,
) {
    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = createAuthorViewModel.getNameValue(),
            onValueChange = {  createAuthorViewModel.setNameValue(it) },
            label = { Text("Nombre del autor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                createAuthorViewModel.saveAuthor()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Guardar Autor")
        }
    }
}