package com.example.goodhabitsapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goodhabitsapp.presentation.screens.SplashScreen


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
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

        }

        //STATISTIC
        composable(route = Screen.Statistic.route) {

        }


        //LIST
        composable(route = Screen.List.route) {

        }

        //TASK
        composable(route = Screen.Task.route) {

        }




    }

}