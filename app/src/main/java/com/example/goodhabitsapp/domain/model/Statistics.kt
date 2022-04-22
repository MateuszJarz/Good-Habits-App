package com.example.goodhabitsapp.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.goodhabitsapp.ui.theme.HighPriorityColor
import com.example.goodhabitsapp.ui.theme.LowPriorityColor
import com.example.goodhabitsapp.ui.theme.MediumPriorityColor
import com.example.goodhabitsapp.ui.theme.NonePriorityColor
import com.example.goodhabitsapp.util.Constants.STAT_DATABASE_TABLE

/*enum class Statistics(number : Int) {
    TASK_COMPLETED(0) ,
    TASK_NON_COMPLETED(0),
    ZERO(0)

}*/


@Entity(tableName = STAT_DATABASE_TABLE)
data class Statistics(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val tasksCompleted: Int = 0,
    val tasksNotCompleted: Int = 0,
)


