package com.example.pokedex_kotlin.model.entities

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