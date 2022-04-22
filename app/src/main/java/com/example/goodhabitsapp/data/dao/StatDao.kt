package com.example.goodhabitsapp.data.dao

import androidx.room.*
import com.example.goodhabitsapp.domain.model.Statistics
import com.example.goodhabitsapp.domain.model.Task
import com.example.goodhabitsapp.util.Constants.STAT_DATABASE_TABLE

@Dao
interface StatDao {

    @Insert
     fun insertStatTable(statistics: Statistics)

    @Query("UPDATE statistic_table SET tasksCompleted = tasksCompleted + 1")
    suspend fun updateStat()


}