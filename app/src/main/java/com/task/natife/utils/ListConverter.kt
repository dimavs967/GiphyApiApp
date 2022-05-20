package com.task.natife.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.natife.model.GifListModel

class ListConverter {

    private val gson = Gson()

    @TypeConverter
    fun listToString(value: List<GifListModel>): String {
        val type = object : TypeToken<List<GifListModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<GifListModel> {
        val type = object : TypeToken<List<GifListModel>>() {}.type
        return gson.fromJson(value, type)
    }

//    @TypeConverter
//    fun fromString(value: String?): List<String?>? {
//        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: ArrayList<String?>?): String? {
//        return Gson().toJson(list)
//    }

}