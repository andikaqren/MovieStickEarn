package com.steve.moviestickearn.core.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object DataConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): List<Int> {
        val listType =
            object : TypeToken<List<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: List<Int?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}