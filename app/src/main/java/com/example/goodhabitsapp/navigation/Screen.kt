package com.example.goodhabitsapp.navigation

import com.example.goodhabitsapp.util.Action

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")

    object Timer : Screen("timer_screen")

    object Statistic : Screen("statistic_screen")

    object List : Screen("list_screen/{action}") {
        fun passAction(action: Action): String {
            return "list_screen/${action.name}"
        }
    }

    object Task : Screen("task_screen/{taskId}") {
        fun passTaskId(taskId: Int): String {
            return "task_screen/${taskId}"
        }

    }
}
