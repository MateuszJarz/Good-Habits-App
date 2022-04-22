package com.example.goodhabitsapp.data.repository

import com.example.goodhabitsapp.data.dao.StatDao
import com.example.goodhabitsapp.domain.model.Statistics
import javax.inject.Inject

class StatRepository @Inject constructor(
    private val statDao: StatDao
) {
    suspend fun tasksCompleted() {
        return statDao.tasksCompleted()
    }

    suspend fun tasksNotCompleted() {
        return statDao.tasksNotCompleted()
    }


    suspend fun getStats(): Statistics {
        return statDao.getStats()
    }
}