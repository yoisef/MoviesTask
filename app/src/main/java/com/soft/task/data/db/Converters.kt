package com.soft.task.data.db

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {



    @TypeConverter
    fun toListDayJson(meaning: List<Int>): String {
        val gson = Gson()
        return gson.toJson(meaning)
    }

    @TypeConverter
    fun toListDasObject(json: String): List<Int> {
        val gson = Gson()
        val listtype = object : TypeToken<kotlin.collections.List<Int>>() {}.type


        return gson.fromJson<kotlin.collections.List<Int>>(json, listtype)

    }






}