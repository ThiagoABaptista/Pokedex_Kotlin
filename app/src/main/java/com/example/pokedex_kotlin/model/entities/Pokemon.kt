package com.example.pokedex_kotlin.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
@Entity(tableName = "pokemons_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    @ColumnInfo val name :String,
    @ColumnInfo(name = "type_of_pokemon") val typeOfPokemon: ArrayList<String>,
    @ColumnInfo val weaknesses : ArrayList<String>,
    @ColumnInfo val evolutions: ArrayList<String>,
    @ColumnInfo val category :String,
    @ColumnInfo(name = "x_description") val xDescription:String,
    @ColumnInfo(name = "image_source") val imageSource: String,
    @ColumnInfo(name = "catched_pokemon") val catchedPokemon: Boolean = false
) : Parcelable,Serializable{
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

data class Pokemon(
    val id :Int,
    val name :String,
    val typeOfPokemon: ArrayList<String>,
   val weaknesses : ArrayList<String>,
    val evolutions: ArrayList<String>,
    val category :String,
    val xDescription:String,
    val imageSource: String,
    val catchedPokemon: Boolean = false
) :  Serializable{
    val gifUrl get():String {
        var cleanName = name.replace("-","")
            .replace(" ", "")
            .replace(".","")
            .replace("'","")
            .lowercase(Locale.getDefault())

        return "https://play.pokemonshowdown.com/sprites/xyani/$cleanName.gif"
    }
}
*/
