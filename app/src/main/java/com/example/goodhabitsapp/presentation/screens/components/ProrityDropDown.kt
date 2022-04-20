package com.example.goodhabitsapp.presentation.screens.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodhabitsapp.domain.model.Priority
import com.example.goodhabitsapp.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.example.goodhabitsapp.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expended by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expended) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable {
                expended = true
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier
                .weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .weight(weight = 1.5f)
                .rotate(degrees = angle),

            onClick = {
                expended = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop Dwon Arrow"
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94f),
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onPrioritySelected(Priority.LOW)
                }
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onPrioritySelected(Priority.MEDIUM)
                }
            ) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(
                onClick = {
                    expended = false
                    onPrioritySelected(Priority.HIGH)
                }
            ) {
                PriorityItem(priority = Priority.HIGH)
            }
        }

    }
}

@Preview
@Composable
fun PriorityDropDownPreview() {
    PriorityDropDown(
        priority = Priority.HIGH,
        onPrioritySelected = {}
    )
}