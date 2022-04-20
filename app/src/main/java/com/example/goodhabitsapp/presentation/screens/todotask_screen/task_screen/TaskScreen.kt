package com.example.goodhabitsapp.presentation.screens.todotask_screen.task_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.domain.model.Task
import com.example.goodhabitsapp.presentation.navigation.Screen
import com.example.goodhabitsapp.util.Action
import com.example.goodhabitsapp.view_models.TaskViewModel

@Composable
fun TaskScreen(
    selectedTask: Task?,
    taskViewModel: TaskViewModel,
    navController: NavController,

    ) {

    val title: String by taskViewModel.title
    val description: String by taskViewModel.description
    val priority: Priority by taskViewModel.priority
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {

                        navController.navigate(Screen.List.passAction(action = action))
                    } else {
                        if (taskViewModel.validateFields()) {
                            navController.navigate(Screen.List.passAction(action = action))

                        } else {
                            displayToast(context = context)
                        }
                    }
                },

                )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    taskViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    taskViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    taskViewModel.priority.value = it
                })
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields Empty.", Toast.LENGTH_SHORT).show()
}