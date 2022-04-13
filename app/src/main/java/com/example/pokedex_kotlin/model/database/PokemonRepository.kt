package com.example.pokedex_kotlin.model.database

import androidx.annotation.WorkerThread
import com.example.pokedex_kotlin.model.entities.Pokemon

class PokemonRepository(private val pokemonDao: PokemonDao) {

    @WorkerThread
    suspend fun insertPokemonData(pokemon: Pokemon){
        pokemonDao.insertPokemonDetails(pokemon)
    }

}