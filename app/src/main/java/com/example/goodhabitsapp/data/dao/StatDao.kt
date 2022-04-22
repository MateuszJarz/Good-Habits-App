package com.example.goodhabitsapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.goodhabitsapp.domain.model.Statistics

@Dao
interface StatDao {

    @Insert
    fun insertStatTable(statistics: Statistics)

    @Query("UPDATE statistic_table SET tasksCompleted = tasksCompleted + 1")
    suspend fun tasksCompleted()

    @Query("UPDATE statistic_table SET tasksNotCompleted = tasksNotCompleted + 1")
    suspend fun tasksNotCompleted()

    @Query("SELECT * FROM statistic_table ORDER BY id ASC")
    suspend fun getStats(): Statistics


}