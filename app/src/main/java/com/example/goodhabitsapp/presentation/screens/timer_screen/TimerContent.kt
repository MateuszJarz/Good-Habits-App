package com.example.goodhabitsapp.presentation.screens.timer_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodhabitsapp.ui.theme.fabBackgroundColor
import com.example.goodhabitsapp.ui.theme.topAppBarBackgroundColor

@Composable
fun TimerScreenContent(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    optionSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.topAppBarBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Pomodoro Timer",
            color = Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,


            )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "1 minute to launch...",
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,

            )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Click to start to do Pomodoro",
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,

            )

        CountDownIndicator(
            Modifier.padding(top = 50.dp),
            progress = progress,
            time = time,
            size = 250,
            stroke = 12
        )

        CountDownButton(

            modifier = Modifier
                .padding(top = 70.dp)
                .size(70.dp),
            isPlaying = isPlaying
        ) {
            optionSelected()
        }


    }

}

@Composable
fun CountDownIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    time: String,
    size: Int,
    stroke: Int
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Column(modifier = modifier) {
        Box {

            CircularProgressIndicatorBackGround(
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = MaterialTheme.colors.background,
                stroke
            )

            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                color = Color.Red,
                strokeWidth = stroke.dp,
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = time,
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier,
    color: Color,
    stroke: Int
) {
    val style = with(LocalDensity.current) { Stroke(stroke.dp.toPx()) }

    Canvas(modifier = modifier, onDraw = {

        val innerRadius = (size.minDimension - style.width) / 2
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = Offset(
                (size / 2.0f).width - innerRadius,
                (size / 2.0f).height - innerRadius
            ),
            size = Size(innerRadius * 2, innerRadius * 2),
            useCenter = false,
            style = style
        )

    })
}

@Composable
fun CountDownButton(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    optionSelected: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 90.dp)
    )
    {

        Button(
            onClick = {
                optionSelected()
            },
            modifier =
            Modifier
                .height(30.dp)
                .width(150.dp),

            shape = RoundedCornerShape(25.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.fabBackgroundColor,
                contentColor = Color.White,
            ),

            )

        {
            val pair = if (!isPlaying) {
                "START"
            } else {
                "STOP"
            }

            Text(
                pair,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }


}


@Preview
@Composable
fun TimerScreenPrev() {

}

