package com.example.goodhabitsapp.presentation.screens.todotask_screen.list_screen


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.domain.model.Task
import com.example.goodhabitsapp.ui.theme.*
import com.example.goodhabitsapp.util.Action
import com.example.goodhabitsapp.util.RequestState
import com.example.goodhabitsapp.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListContent(
    allTasks: RequestState<List<Task>>,
    searchTasks: RequestState<List<Task>>,
    lowPriorityTask: List<Task>,
    highPriorityTask: List<Task>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToDoTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, Task) -> Unit

) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToDoTaskScreen = navigateToDoTaskScreen
                    )
                }
            }
            sortState.data == Priority.NONE -> {

                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToDoTaskScreen = navigateToDoTaskScreen

                    )
                }
            }
            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTask,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToDoTaskScreen = navigateToDoTaskScreen
                )
            }
            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTask,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToDoTaskScreen = navigateToDoTaskScreen

                )
            }
        }


    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    tasks: List<Task>,
    onSwipeToDelete: (Action, Task) -> Unit,
    navigateToDoTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToDoTaskScreen = navigateToDoTaskScreen


        )
    }

}


@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks: List<Task>,
    onSwipeToDelete: (Action, Task) -> Unit,
    navigateToDoTaskScreen: (taskId: Int) -> Unit,
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->

            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val scope = rememberCoroutineScope()
                scope.launch {
                    delay(300)
                    onSwipeToDelete(Action.DELETE, task)
                }


            }

            val degrees by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -45f
            )
            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                    background = { RedBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(
                            toDoTask = task,
                            navigateToDoTaskScreen = navigateToDoTaskScreen
                        )
                    }
                )
            }


        }
    }
}


@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = HighPriorityColor)
            .padding(horizontal = EXTRA_LARGE_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = Color.White
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    toDoTask: Task,
    navigateToDoTaskScreen: (taskId: Int) -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {

            navigateToDoTaskScreen(toDoTask.id)
        }
    ) {
      
      Row(modifier = Modifier.fillMaxWidth().height(65.dp)
      
      ) {
          
          Column(
              Modifier
                  .fillMaxWidth(0.8f)
                  .padding(all = LARGE_PADDING)
                 // .fillMaxWidth()
          ) {

              Row {
                  Text(
                      modifier = Modifier.weight(8f),
                      text = toDoTask.title,
                      color = MaterialTheme.colors.taskItemTextColor,
                      style = MaterialTheme.typography.h5,
                      fontWeight = FontWeight.Bold,
                      maxLines = 1
                  )
                  Box(
                      modifier = Modifier
                          .fillMaxWidth()
                          .weight(1f),
                      contentAlignment = Alignment.TopEnd
                  ) {
                      Canvas(
                          modifier = Modifier
                              .width(PRIORITY_INDICATOR_SIZE)
                              .height(
                                  PRIORITY_INDICATOR_SIZE
                              )
                      ) {
                          drawCircle(
                              color = toDoTask.priority.color
                          )
                      }
                  }
              }
              Text(
                  modifier = Modifier.fillMaxWidth(0.8f),
                  color = MaterialTheme.colors.taskItemTextColor,
                  text = toDoTask.description,
                  style = MaterialTheme.typography.subtitle1,
                  maxLines = 2,
                  overflow = TextOverflow.Ellipsis
              )


          }
          Box(modifier = Modifier
              .fillMaxSize()
              .background(Purple200.copy(ContentAlpha.medium))
          ,) {

              Checkbox(
                  modifier = Modifier.align(Alignment.Center),
                  checked = false, onCheckedChange ={}
              )
          }
      }
       
        
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TaskItemPrev() {

    TaskItem(
        toDoTask = Task(
            id = 1,
            title = "Test1",
            description = "Some random text",
            priority = Priority.HIGH,
            taskCompleted = 0,
            taskNotCompleted = 0
        ),
        navigateToDoTaskScreen = {})


}

