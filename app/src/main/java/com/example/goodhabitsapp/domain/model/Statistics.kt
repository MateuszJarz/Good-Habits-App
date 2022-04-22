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
/* TODO
@Entity(tableName = STAT_DATABASE_TABLE)
data class Statistics(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskId: Int = 0,
    val tasksCompleted: Int = 0,
    val tasksNotCompleted: Int = 0,
)

data class TaskAndStatsRelation(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val statistics: Statistics
)*/
