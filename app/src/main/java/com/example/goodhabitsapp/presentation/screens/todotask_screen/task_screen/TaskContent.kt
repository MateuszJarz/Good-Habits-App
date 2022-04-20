package com.example.goodhabitsapp.presentation.screens.todotask_screen.task_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.presentation.screens.components.PriorityDropDown
import com.example.goodhabitsapp.ui.theme.LARGE_PADDING
import com.example.goodhabitsapp.ui.theme.MEDIUM_PADDING


@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = "title") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        Divider(modifier = Modifier.height(MEDIUM_PADDING), color = MaterialTheme.colors.background)
        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = "description") },
            textStyle = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.HIGH,
        onPrioritySelected = {})
}