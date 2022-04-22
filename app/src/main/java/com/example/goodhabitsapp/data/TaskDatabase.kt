package com.example.goodhabitsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goodhabitsapp.data.dao.StatDao
import com.example.goodhabitsapp.data.dao.TaskDao
import com.example.goodhabitsapp.domain.model.Statistics
import com.example.goodhabitsapp.domain.model.Task

@Database(entities = [Task::class, Statistics::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun statDao(): StatDao
}