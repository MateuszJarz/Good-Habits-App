package com.example.goodhabitsapp.di

import android.content.Context
import androidx.room.Room
import com.example.goodhabitsapp.data.TaskDatabase
import com.example.goodhabitsapp.util.Constants.TASK_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        TASK_DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideDao(database: TaskDatabase) = database.taskDao()
}