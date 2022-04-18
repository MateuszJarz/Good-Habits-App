package com.example.goodhabitsapp.data

import androidx.room.*
import com.example.goodhabitsapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)


    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<Task>

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM task_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDataBase(searchQuery: String): Flow<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): Flow<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): Flow<List<Task>>

}