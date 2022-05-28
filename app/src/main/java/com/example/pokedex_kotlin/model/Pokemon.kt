package com.example.pokedex_kotlin.model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Pokemon(
    val id :String,
    val name :String,
    val typeofpokemon: ArrayList<String>,
    val weaknesses : ArrayList<String>,
    val evolutions: List<String>,
    val category :String,
    val xDescription:String,
    val imageurl: String,
    val catchedPokemon: Boolean = false
) : Serializable{
    val cleanIdEvolution get():List<String> {
        return evolutions.map { id -> id.replace("#","") }
    }
    val gifUrl get():String {
        var cleanName = name.replace("-","")
            .replace(" ", "")
            .replace(".","")
            .replace("'","")
            .lowercase(Locale.getDefault())

        return "https://play.pokemonshowdown.com/sprites/xyani/$cleanName.gif"
    }
}

/*
@Parcelize
@Entity(tableName = "pokemons_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    @ColumnInfo val name :String,
    @ColumnInfo(name = "type_of_pokemon") val typeOfPokemon: ArrayList<String>,
    @ColumnInfo val weaknesses : ArrayList<String>,
    @ColumnInfo val evolutions: List<String>,
    @ColumnInfo val category :String,
    @ColumnInfo(name = "x_description") val xDescription:String,
    @ColumnInfo(name = "image_source") val imageSource: String,
    @ColumnInfo(name = "catched_pokemon") val catchedPokemon: Boolean = false
)
*/