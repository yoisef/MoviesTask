package com.soft.task.di

import android.content.Context
import androidx.room.Room
import com.soft.task.data.db.MovieDao
import com.soft.task.data.db.MovieDatabase
import com.soft.task.utils.Database_Name


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext appContext: Context): MovieDatabase {
            return Room.databaseBuilder(
                appContext,
                MovieDatabase::class.java,
                Database_Name
            ).build()
        }

        @Provides
        fun provideLogDao(database: MovieDatabase): MovieDao {
            return database.getDao()
        }


}