package com.soft.task.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soft.task.data.db.Converters
import com.soft.task.data.db.CityDao
import com.soft.task.domain.models.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun getDao(): CityDao






}