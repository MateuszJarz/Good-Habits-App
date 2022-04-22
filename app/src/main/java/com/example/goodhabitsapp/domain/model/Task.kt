package com.example.goodhabitsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.goodhabitsapp.util.Constants.TASK_DATABASE_TABLE

@Entity(tableName = TASK_DATABASE_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val statistics: Statistics
)
