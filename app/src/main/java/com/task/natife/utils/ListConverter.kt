package com.task.natife.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.natife.model.GifModel

class ListConverter {

    private val gson = Gson()

    @TypeConverter
    fun listToString(value: List<GifModel>): String {
        val type = object : TypeToken<List<GifModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<GifModel> {
        val type = object : TypeToken<List<GifModel>>() {}.type
        return gson.fromJson(value, type)
    }

}