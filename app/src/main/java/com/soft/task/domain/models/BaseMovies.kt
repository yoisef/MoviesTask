package com.soft.task.domain.models

data class BaseMovies(
    val id: Int,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)