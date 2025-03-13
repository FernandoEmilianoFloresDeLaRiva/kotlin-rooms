package com.example.room_clase.create_book.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.room_clase.create_author.ui.viewmodels.CreateAuthorViewModel
import com.example.room_clase.create_book.ui.viewmodels.CreateBookViewModel

@Composable
fun CreateBookPage(
    createBookViewModel: CreateBookViewModel,
    createAuthorVM : CreateAuthorViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit){
        createAuthorVM.getAuthors()
    }
    val authors = createAuthorVM.authors.value

    Column(modifier = modifier.padding(16.dp)) {

        OutlinedTextField(
            value = createBookViewModel.getTitleValue(),
            onValueChange = { createBookViewModel.setTitleValue(it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = createAuthorVM.findAuthorById(createBookViewModel.getSelectedAuthorValue())?.name ?: "",
                onValueChange = {},
                label = { Text("Author") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {
                        createBookViewModel.setExpandedValue(!createBookViewModel.getExpandedValue())
                    }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Expand")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = createBookViewModel.getExpandedValue(),
                onDismissRequest = { createBookViewModel.setExpandedValue(false) }
            ) {
                authors.forEach { author ->
                    DropdownMenuItem(
                        text = { Text(author.name) },
                        onClick = {
                            createBookViewModel.setSelectedAuthor(author.id)
                            createBookViewModel.setExpandedValue(false)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = createBookViewModel.getContentValue(),
            onValueChange = { createBookViewModel.setContentValue(it) },
            label = { Text("Content") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = createBookViewModel.getYearValue(),
            onValueChange = { createBookViewModel.setYearValue(it) },
            label = { Text("Year") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                createBookViewModel.saveBook()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = createBookViewModel.getTitleValue().isNotBlank() && createBookViewModel.getYearValue().isNotBlank()
        ) {
            Text("Guardar libro")
        }
    }
}