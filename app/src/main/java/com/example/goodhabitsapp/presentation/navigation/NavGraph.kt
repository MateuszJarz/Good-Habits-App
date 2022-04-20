package com.example.goodhabitsapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goodhabitsapp.presentation.screens.SplashScreen
import com.example.goodhabitsapp.presentation.screens.statistic_screen.StatisticScreen
import com.example.goodhabitsapp.presentation.screens.timer_screen.TimerScreen
import com.example.goodhabitsapp.presentation.screens.todotask_screen.list_screen.ListScreen
import com.example.goodhabitsapp.presentation.screens.todotask_screen.task_screen.TaskScreen
import com.example.goodhabitsapp.util.Constants
import com.example.goodhabitsapp.util.toAction
import com.example.goodhabitsapp.view_models.TaskViewModel
import com.example.goodhabitsapp.view_models.TimerViewModel


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    timerViewModel: TimerViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        //SPLASH
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        //TIMER
        composable(route = Screen.Timer.route) {
            TimerScreen(
                navController = navController,
                timerViewModel = timerViewModel
            )
        }

        //STATISTIC
        composable(route = Screen.Statistic.route) {
            StatisticScreen(navController = navController)
        }


        //LIST
        composable(route = Screen.List.route) { navBackStackEntry ->

            val action =
                navBackStackEntry.arguments?.getString(Constants.LIST_ARGUMENT_KEY).toAction()

            LaunchedEffect(key1 = action) {
                taskViewModel.action.value = action
            }
            ListScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

        //TASK
        composable(route = Screen.Task.route) { navBackStackEntry ->
            val taskId =
                navBackStackEntry.arguments!!.getString(Constants.TASK_ARGUMENT_KEY)!!.toInt()
            LaunchedEffect(key1 = taskId) {
                taskViewModel.getSelectedTask(taskId = taskId)
            }
            val selectedTask by taskViewModel.selectedTask.collectAsState()

            LaunchedEffect(key1 = selectedTask) {
                if (selectedTask != null || taskId == -1) {
                    taskViewModel.updateTaskFields(selectedTask = selectedTask)
                }
            }
            TaskScreen(
                selectedTask = selectedTask,
                taskViewModel = taskViewModel,
                navController = navController
            )
        }


    }

}