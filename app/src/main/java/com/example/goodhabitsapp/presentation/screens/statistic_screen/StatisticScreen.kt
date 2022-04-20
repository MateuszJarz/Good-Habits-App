package com.example.goodhabitsapp.presentation.screens.statistic_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.goodhabitsapp.presentation.navigation.Screen
import com.example.goodhabitsapp.presentation.screens.components.app_bars.BottomMenuBar

@Composable
fun StatisticScreen(
    navController: NavHostController
) {

    Scaffold(
        bottomBar = {
            BottomMenuBar(
                onClickedList = { navController.navigate(Screen.List.route) },
                onClickedPomodoro = { navController.navigate(Screen.Timer.route) },
                onClickedStatistic = { navController.navigate(Screen.Statistic.route) }
            )
        }) {

    }
}