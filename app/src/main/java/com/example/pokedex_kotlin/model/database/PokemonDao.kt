package com.example.pokedex_kotlin.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex_kotlin.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertPokemonDetails(pokemon: Pokemon)

    @Query("SELECT * FROM POKEMONS_TABLE ORDER BY ID")
    fun getAllPokemonList(): Flow<Pokemon>

    @Query("SELECT * FROM POKEMONS_TABLE WHERE catched_pokemon = 1")
    fun getCatchedPokemonList() : Flow<List<Pokemon>>
}