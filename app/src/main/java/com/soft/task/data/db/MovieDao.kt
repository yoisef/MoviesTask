package com.soft.task.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

import com.soft.task.domain.models.Movie


@Dao
interface MovieDao {


    @Query("SELECT * FROM movies")
    fun getSavedCities() :LiveData<List<Movie>>

    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies : Movie)


    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()


    @Query("SELECT * FROM movies WHERE title LIKE :name || '%'")
    suspend fun searchMovie(name:String): Movie


}