package com.example.goodhabitsapp.presentation.screens.todotask_screen.task_screen

import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodhabitsapp.R
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.domain.model.Task
import com.example.goodhabitsapp.presentation.screens.components.DisplayAlertDialog
import com.example.goodhabitsapp.ui.theme.topAppBarBackgroundColor
import com.example.goodhabitsapp.ui.theme.topAppBarContentColor
import com.example.goodhabitsapp.util.Action

@Composable
fun TaskAppBar(
    selectedTask: Task?,
    navigateToListScreen: (Action) -> Unit,
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {

        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit,

    ) {

    TopAppBar(
        navigationIcon = {
            BackAction(
                onBackClicked = navigateToListScreen
            )
        },
        title = {
            Text(
                text = "Add Task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )

}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {

            onBackClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Arrow",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {

            onAddClicked(Action.ADD)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Add Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Composable
fun ExistingTaskAppBar(
    selectedTask: Task,
    navigateToListScreen: (Action) -> Unit,
) {

    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        }
    )

}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: Task,
    navigateToListScreen: (Action) -> Unit,
) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, selectedTask.title),
        message = stringResource(id = R.string.delete_task_confirmation, selectedTask.title),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = {
            navigateToListScreen(Action.DELETE)
        }
    )

    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onDeleteClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onCloseClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onUpdateClicked(Action.UPDATE)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Updated Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Preview
@Composable
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}


@Preview
@Composable
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        navigateToListScreen = {},
        selectedTask = Task(
            0,
            "title",
            "description",
            Priority.HIGH,
            statistics = Statistics.ZERO
        )
    )
}