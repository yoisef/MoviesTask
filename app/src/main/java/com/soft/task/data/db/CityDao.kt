package com.soft.task.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

import com.soft.task.domain.models.Movie


@Dao
interface CityDao {


    @Query("SELECT * FROM movies")
    fun getSavedCities() :LiveData<List<Movie>>

    @Query("SELECT * FROM movies")
    suspend fun getCities() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cities : Movie)


    @Query("DELETE FROM movies")
    suspend fun deleteAllCities()

    /*
    @Query("SELECT * FROM movies WHERE name LIKE :name || '%'")
    suspend fun searchCity(name:String): Movie

     */
}