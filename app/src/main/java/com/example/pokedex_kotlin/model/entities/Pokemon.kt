package com.example.pokedex_kotlin.model.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
@Parcelize
// Define the Table name
@Entity(tableName = "pokemons_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true) val id : String,
    @ColumnInfo val name :String,
    @ColumnInfo val typeofpokemon: ArrayList<String>,
    @ColumnInfo val weaknesses : ArrayList<String>,
    @ColumnInfo val evolutions: List<String>,
    @ColumnInfo val category :String,
    @ColumnInfo(name = "x_description") val xdescription:String,
    @ColumnInfo val imageurl: String,
    @ColumnInfo(name = "catched_pokemon") val catchedPokemon: Boolean = false
) : Parcelable{
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