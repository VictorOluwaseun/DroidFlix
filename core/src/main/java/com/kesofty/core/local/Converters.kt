package com.kesofty.core.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kesofty.core.util.json.JsonParser

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromGenreIds(json: String): List<Int> {
        return jsonParser.fromJson<ArrayList<Int>>(
            json,
            object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toGenreIds(genreIds: List<Int>): String {
        return jsonParser.toJson(
            genreIds,
            object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: "[]"
    }
}