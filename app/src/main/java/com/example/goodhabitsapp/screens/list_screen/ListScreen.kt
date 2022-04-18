package com.example.goodhabitsapp.screens.list_screen

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.goodhabitsapp.navigation.Screen
import com.example.goodhabitsapp.screens.components.app_bars.BottomMenuBar
import com.example.goodhabitsapp.screens.components.app_bars.ListAppBar
import com.example.goodhabitsapp.ui.theme.fabBackgroundColor
import com.example.goodhabitsapp.util.Action
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navController: NavHostController,
  //  navigateToTaskScreen: (taskId: Int) -> Unit,
  //  sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        Log.d("ListScreen", "LaunchedEffect Triggered!")
      //  sharedViewModel.getAllTask()
      //  sharedViewModel.readSortState()
    }
/*
    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTask.collectAsState()
    val searchTasks by sharedViewModel.searchTask.collectAsState()

    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTask.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTask.collectAsState()

    val searchAppBarState: SearchAppBarState
            by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState*/

    val scaffoldState = rememberScaffoldState()


   /* TODO DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action = action) },
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )*/
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
              /* TODO  sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState,
                navController = navController */
            )
        },
        bottomBar = {
            BottomMenuBar(
                onClickedList = {
                    navController.navigate(Screen.List.route)
                },
                onClickedPomodoro = {
                    navController.navigate(Screen.Timer.route)

                },
                onClickedStatistic = {
                    navController.navigate(Screen.Statistic.route)
                }
            )
        },

        content = {
           /* TODO ListContent(
                allTasks = allTasks,
                searchTasks = searchTasks,
                lowPriorityTask = lowPriorityTasks,
                highPriorityTask = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                navigateToDoTaskScreen = {  taskId ->
                    navController.navigate(Screen.Task.passTaskId(taskId))
                    Log.d("taskId",taskId.toString())
                },
                onSwipeToDelete = { action, task ->
                    sharedViewModel.action.value = action
                    sharedViewModel.updateTaskFields(selectedTask = task)
                },
                )*/
        },
        floatingActionButton = {
            ListFab(onFabClick = { taskId ->
                navController.navigate(Screen.Task.passTaskId(taskId))
            })
        }
    )

}

@Composable
fun ListFab(
    onFabClick: (taskId: Int) -> Unit,
) {
    FloatingActionButton(
        onClick = {

            onFabClick(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Icon",
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeleteTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Task Removed"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeleteTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}
