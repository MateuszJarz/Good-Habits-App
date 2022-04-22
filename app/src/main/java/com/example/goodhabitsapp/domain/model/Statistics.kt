package com.example.goodhabitsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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


