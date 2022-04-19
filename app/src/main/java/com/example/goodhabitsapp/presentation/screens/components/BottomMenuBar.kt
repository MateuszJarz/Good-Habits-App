package com.example.goodhabitsapp.presentation.screens.components.app_bars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodhabitsapp.R
import com.example.goodhabitsapp.ui.theme.topAppBarBackgroundColor

@Composable
fun BottomMenuBar(
    onClickedList: () -> Unit,
    onClickedPomodoro: () -> Unit,
    onClickedStatistic: () -> Unit,
) {
    BottomMenuBarComponent(
        onClickedList = onClickedList,
        onClickedTimer = onClickedPomodoro,
        onClickedStatistic = onClickedStatistic,
    )
}

@Composable
fun BottomMenuBarComponent(
    onClickedList: () -> Unit,
    onClickedTimer: () -> Unit,
    onClickedStatistic: () -> Unit,
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {onClickedList()}
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Task Screen Icon"
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {onClickedTimer()}
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_schedule),
                contentDescription = "Pomodoro Screen Icon"
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {onClickedStatistic()}
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_bar_chart),
                contentDescription = "Statistic Screen Icon"
            )
        }

    }
}

@Preview
@Composable
fun BottomMenuBarPrev() {
    BottomMenuBar(
        onClickedList = {},
        onClickedPomodoro = {},
        onClickedStatistic = {}
    )
}