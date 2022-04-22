package com.example.pokedex_kotlin.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex_kotlin.model.entities.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertPokemonDetails(pokemon: Pokemon)

    @Query("SELECT * FROM POKEMONS_TABLE ORDER BY ID")
    fun getAllPokemonsList(): Flow<Pokemon>

    @Query("SELECT * FROM POKEMONS_TABLE WHERE catched_pokemon = 1")
    fun getCatchedPokemonsList() : Flow<List<Pokemon>>

    @Query("SELECT * FROM POKEMONS_TABLE WHERE CHARINDEX(:filterType,type_of_pokemon) > 0")
    fun getFilteredPokemonsList(filterType : String) : Flow<List<Pokemon>>

}