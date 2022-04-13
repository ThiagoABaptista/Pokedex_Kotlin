package com.example.pokedex_kotlin.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    @ColumnInfo val name :String,
    @ColumnInfo(name = "type_of_pokemon") val typeOfPokemon: ArrayList<String>,
    @ColumnInfo val weaknesses : ArrayList<String>,
    @ColumnInfo val category :String,
    @ColumnInfo val evolutions: List<String>,
    @ColumnInfo(name = "x_description") val xDescription:String,
    @ColumnInfo(name = "image_source") val imageSource: String,
    @ColumnInfo(name = "catched_pokemon") val catchedPokemon: Boolean = false
)
