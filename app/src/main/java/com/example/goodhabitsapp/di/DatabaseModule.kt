package com.example.goodhabitsapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.goodhabitsapp.data.TaskDatabase
import com.example.goodhabitsapp.domain.model.Statistics
import com.example.goodhabitsapp.util.Constants.TASK_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Volatile
    private var INSTANCE: TaskDatabase? = null
    fun getInstance(context: Context): TaskDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: provideDatabase(context).also { INSTANCE = it }
        }


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        TASK_DATABASE_NAME
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Executors.newSingleThreadScheduledExecutor()
                .execute(Runnable {
                    getInstance(context = context).statDao().insertStatTable(Statistics(1, 0, 0))
                })
        }
    })
        .build()


    @Singleton
    @Provides
    fun provideTaskDao(database: TaskDatabase) = database.taskDao()

    @Singleton
    @Provides
    fun provideStatsDao(database: TaskDatabase) = database.statDao()
}