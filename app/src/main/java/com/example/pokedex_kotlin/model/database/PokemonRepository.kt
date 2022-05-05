package com.example.pokedex_kotlin.model.database

import androidx.annotation.WorkerThread
import com.example.pokedex_kotlin.model.entities.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonRepository(private val pokemonDao: PokemonDao) {

    val allPokemonsList: Flow<List<Pokemon>> = pokemonDao.getAllPokemonsList()

    @WorkerThread
    suspend fun insertPokemonData(pokemon: Pokemon){
        pokemonDao.insertPokemonDetails(pokemon)
    }
}