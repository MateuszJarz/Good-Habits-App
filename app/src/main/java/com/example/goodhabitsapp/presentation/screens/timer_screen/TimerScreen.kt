package com.example.goodhabitsapp.presentation.screens.timer_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.goodhabitsapp.presentation.navigation.Screen
import com.example.goodhabitsapp.presentation.screens.components.app_bars.BottomMenuBar
import com.example.goodhabitsapp.util.Utility
import com.example.goodhabitsapp.util.Utility.formatTime
import com.example.goodhabitsapp.view_models.TimerViewModel

@Composable
fun TimerScreen(
    navController: NavHostController,
    timerViewModel: TimerViewModel
) {

    val time by timerViewModel.time.collectAsState(Utility.TIME_COUNTDOWN.formatTime())
    val progress by timerViewModel.progress.collectAsState(1.00F)
    val isPlaying by timerViewModel.isPlaying.collectAsState(false)



    Scaffold(

        bottomBar = {
            BottomMenuBar(
                onClickedList = { navController.navigate(Screen.List.route) },
                onClickedPomodoro = { navController.navigate(Screen.Timer.route) },
                onClickedStatistic = { navController.navigate(Screen.Statistic.route) }
            )


        },
        content = {
            TimerScreenContent(
                time = time,
                progress = progress,
                isPlaying = isPlaying
            ) {
                timerViewModel.handleCountDownTimer()
            }
        }
    )
}