package com.example.goodhabitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.goodhabitsapp.presentation.navigation.SetupNavigation
import com.example.goodhabitsapp.ui.theme.GoodHabitsAppTheme
import com.example.goodhabitsapp.view_models.TaskViewModel
import com.example.goodhabitsapp.view_models.TimerViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val taskViewModel: TaskViewModel by viewModels()
    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodHabitsAppTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navController = navController,
                    taskViewModel = taskViewModel,
                    timerViewModel = timerViewModel
                )

            }
        }
    }
}

