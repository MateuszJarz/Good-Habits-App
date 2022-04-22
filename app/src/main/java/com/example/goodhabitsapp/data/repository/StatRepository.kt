package com.example.goodhabitsapp.data.repository

import com.example.goodhabitsapp.data.dao.StatDao
import com.example.goodhabitsapp.domain.model.Statistics
import com.example.goodhabitsapp.domain.model.Task
import javax.inject.Inject

class StatRepository @Inject constructor(
    private val statDao: StatDao
) {
    suspend fun updateStats() {
        return statDao.updateStat()
    }
}