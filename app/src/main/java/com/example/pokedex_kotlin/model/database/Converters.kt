package com.example.pokedex_kotlin.model.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.google.gson.Gson


import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    /*@TypeConverter
    fun fromStringPokemon(value: String?): ArrayList<Pokemon>{
        val listType = object : TypeToken<ArrayList<Pokemon?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromArrayListPokemon(list: ArrayList<Pokemon?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }*/
}