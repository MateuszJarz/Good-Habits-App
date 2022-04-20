package com.example.goodhabitsapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goodhabitsapp.presentation.screens.SplashScreen
import com.example.goodhabitsapp.presentation.screens.list_screen.ListScreen
import com.example.goodhabitsapp.presentation.screens.statistic_screen.StatisticScreen
import com.example.goodhabitsapp.presentation.screens.timer_screen.TimerScreen
import com.example.goodhabitsapp.view_models.TaskViewModel


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    taskViewModel: TaskViewModel
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
            TimerScreen(navController = navController)
        }

        //STATISTIC
        composable(route = Screen.Statistic.route) {
            StatisticScreen(navController = navController)
        }


        //LIST
        composable(route = Screen.List.route) {
            ListScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

        //TASK
        composable(route = Screen.Task.route) {

        }


    }

}