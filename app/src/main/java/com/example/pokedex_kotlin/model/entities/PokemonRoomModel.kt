package com.example.pokedex_kotlin.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pokemons_table")
data class PokemonRoomModel(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo val name :String,
    @ColumnInfo val typeofpokemon: ArrayList<String>,
    @ColumnInfo val weaknesses : ArrayList<String>,
    @ColumnInfo val evolutions: List<String>,
    @ColumnInfo val category :String,
    @ColumnInfo(name = "x_description") val xDescription:String,
    @ColumnInfo val imageurl: String,
    @ColumnInfo(name = "catched_pokemon") val catchedPokemon: Boolean = false
) : Parcelable
