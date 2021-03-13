package ru.friendforpet.data.db

import androidx.room.TypeConverter


class ListTypeConverter {
    @TypeConverter
    fun dbToList(string: String): List<String> = string.split("[■]".toRegex()).toList()

    @TypeConverter
    fun listToDb(list: List<String>): String = list.joinToString(separator = "■")
}