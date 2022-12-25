package com.soft.task.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")

data class Movie(
    @PrimaryKey
    val id: Int?=null,
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val genre_ids: List<Int>?=null,

    val media_type: String?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    val poster_path: String?=null,
    val release_date: String?=null,
    val title: String?=null,
    val video: Boolean?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null
) : java.io.Serializable