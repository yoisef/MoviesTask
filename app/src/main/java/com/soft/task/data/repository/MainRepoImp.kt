package com.soft.task.data.repository

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.soft.task.domain.models.BaseGenre
import com.soft.task.domain.models.BaseMovies
import com.soft.task.domain.repository.MainRepo
import com.soft.task.network.EndPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepoImp @Inject constructor(private val endPoint: EndPoint?) : MainRepo {








    override fun getGenre(): Flow<BaseGenre> =
        flow {
            endPoint?.let { emit(it.getGenre())
            }
    }

    override fun getGenreList(genreId: String): Flow<BaseMovies> =
        flow {
            endPoint?.let { emit(it.getGenreList(genreID = genreId,"1",))
            }
    }
}





