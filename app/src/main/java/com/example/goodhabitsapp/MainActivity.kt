package com.example.goodhabitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.goodhabitsapp.presentation.navigation.SetupNavigation
import com.example.goodhabitsapp.ui.theme.GoodHabitsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodHabitsAppTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navController = navController,

                )

            }
        }
    }
}

