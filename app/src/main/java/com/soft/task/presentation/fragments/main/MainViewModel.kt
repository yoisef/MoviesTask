package com.soft.task.presentation.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.task.domain.models.BaseGenre
import com.soft.task.domain.models.BaseMovies
import com.soft.task.domain.repository.MainRepo
import com.soft.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {



    private val _getGenres= MutableStateFlow<Resource<BaseGenre>>(Resource.error(null,""))
    val getGenres :StateFlow<Resource<BaseGenre>> =_getGenres

    private val _getGenreList= MutableStateFlow<Resource<BaseMovies>>(Resource.error(null,""))
    val getGenreList :StateFlow<Resource<BaseMovies>> =_getGenreList

    private val _getSearchQuery= MutableStateFlow<Resource<BaseMovies>>(Resource.error(null,""))
    val getSearchQuery :StateFlow<Resource<BaseMovies>> =_getSearchQuery

    fun getGenre()
    {
        viewModelScope.launch {
            _getGenres.emit(Resource.loading(null))
            mainRepo.getGenre().collect{

                if (it.genres.isNotEmpty())
                {
                    _getGenres.value=Resource.success(it)
                }else{
                    _getGenres.value=Resource.error(it,"Categories Is Empty")

                }
            }

        }

    }

    fun getGenreList(listId : String)
    {
        viewModelScope.launch {

            _getGenreList.emit(Resource.loading(null))
            mainRepo.getGenreList(listId).collect{
                if (it.results.isNotEmpty())
                {
                    _getGenreList.value=Resource.success(it)
                }else{
                    _getGenreList.value=Resource.error(it,"Properties Is Empty")

                }

            }

        }
    }

    fun getSearchQuery(query :String)
    {
        viewModelScope.launch {
            _getSearchQuery.emit(Resource.loading(null))

            mainRepo.getSearchQuery(query).collect{
                if (it.results.isNotEmpty())
                {
                    _getSearchQuery.value=Resource.success(it)
                }else{
                    _getSearchQuery.value=Resource.error(it,"Search Is Empty")

                }
            }
        }
    }






}