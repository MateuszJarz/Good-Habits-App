package com.example.goodhabitsapp.data.repository

import com.example.goodhabitsapp.data.dao.TaskDao
import com.example.goodhabitsapp.domain.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    val getAllTask: Flow<List<Task>> = taskDao.getAllTask()
    val sortByLowPriority: Flow<List<Task>> = taskDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<Task>> = taskDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<Task> {
        return taskDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(toDoTask: Task) {
        return taskDao.addTask(task = toDoTask)
    }

    suspend fun updateTask(toDoTask: Task) {
        return taskDao.updateTask(task = toDoTask)
    }

    suspend fun deleteTask(toDoTask: Task) {
        return taskDao.deleteTask(task = toDoTask)
    }

    suspend fun deleteAllTask() {
        return taskDao.deleteAllTask()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Task>> {
        return taskDao.searchDataBase(searchQuery = searchQuery)
    }
}