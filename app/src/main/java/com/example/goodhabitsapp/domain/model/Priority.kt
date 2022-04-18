package com.example.goodhabitsapp.domain.model

import androidx.compose.ui.graphics.Color
import com.example.goodhabitsapp.ui.theme.HighPriorityColor
import com.example.goodhabitsapp.ui.theme.LowPriorityColor
import com.example.goodhabitsapp.ui.theme.MediumPriorityColor
import com.example.goodhabitsapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}