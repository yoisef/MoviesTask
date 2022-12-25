package com.soft.task.domain.repository

import com.soft.task.domain.models.BaseGenre
import com.soft.task.domain.models.BaseMovies
import kotlinx.coroutines.flow.Flow

interface MainRepo {

    fun getGenre() : Flow<BaseGenre>

    fun getGenreList(listId : String) : Flow<BaseMovies>

    fun getSearchQuery(listId : String) : Flow<BaseMovies>
}