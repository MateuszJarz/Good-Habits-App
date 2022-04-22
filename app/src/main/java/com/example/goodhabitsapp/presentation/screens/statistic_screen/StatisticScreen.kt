package com.example.goodhabitsapp.presentation.screens.statistic_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.goodhabitsapp.domain.model.Statistics
import com.example.goodhabitsapp.presentation.navigation.Screen
import com.example.goodhabitsapp.presentation.screens.components.app_bars.BottomMenuBar
import com.example.goodhabitsapp.ui.theme.ChartCompletedColor
import com.example.goodhabitsapp.ui.theme.ChartNotCompletedColor
import com.example.goodhabitsapp.ui.theme.SMALL_PADDING
import com.example.goodhabitsapp.view_models.StatsViewModel


@Composable
fun StatisticScreen(
    navController: NavHostController,
    statsViewModel: StatsViewModel
) {
    LaunchedEffect(
        key1 = true
    ) {
        statsViewModel.getStats()
    }

    val taskStats by statsViewModel.allStats.collectAsState()

    Log.d("tasksCompleted", taskStats.tasksCompleted.toString())
    Log.d("tasksNonCompleted", taskStats.tasksNotCompleted.toString())

    Scaffold(
        bottomBar = {
            BottomMenuBar(
                onClickedList = { navController.navigate(Screen.List.route) },
                onClickedPomodoro = { navController.navigate(Screen.Timer.route) },
                onClickedStatistic = { navController.navigate(Screen.Statistic.route) }
            )
        },
        content = {
            StatisticContent(statistics = taskStats)
        }
    )


}

@Composable
fun StatisticContent(statistics: Statistics) {
    Column(
        modifier = Modifier.padding(all = SMALL_PADDING),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Statistic of completed task",
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
        PiaChart(statistics = statistics)
    }

}

@Composable
fun PiaChart(statistics: Statistics) {
    val context = LocalContext.current
    val points = listOf(statistics.tasksCompleted.toFloat(), statistics.tasksNotCompleted.toFloat())
    val color = listOf(
        ChartCompletedColor,
        ChartNotCompletedColor
    )
    val sum = points.sum()
    var startAngle = 0f
    val radius = 350f
    val rect = Rect(Offset(-radius, -radius), Size(2 * radius, 2 * radius))
    val path = Path()

    val angles = mutableListOf<Float>()

    var start by remember { mutableStateOf(false) }
    val sweepPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 70.dp)
            .height(350.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val x = it.x - radius
                        val y = it.y - radius
                        var touchAngle = Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()))
                        if (x < 0 && y < 0 || x > 0 && y < 0) {
                            touchAngle += 360
                        }
                        val position =
                            getPositionFromAngle(touchAngle = touchAngle, angles = angles)
                        Toast
                            .makeText(context, "onTap: $points", Toast.LENGTH_SHORT)
                            .show()
                    }
                )


            },

        ) {
        translate(radius, radius) {
            start = true
            for ((i, p) in points.withIndex()) {

                val sweepAngle = p / sum * 360f
                path.moveTo(0f, 0f)
                path.arcTo(
                    rect = rect,
                    startAngleDegrees = startAngle,
                    sweepAngleDegrees = sweepAngle * sweepPre,
                    false
                )
                angles.add(sweepAngle)
                drawPath(path = path, color = color[i])
                path.reset()
                startAngle += sweepAngle
            }
        }
    }

}

private fun getPositionFromAngle(
    angles: List<Float>,
    touchAngle: Double
): Int {
    var totalAngle = 0f
    for ((i, angle) in angles.withIndex()) {
        totalAngle += angle
        if (touchAngle <= totalAngle) {
            return i
        }
    }
    return -1
}
