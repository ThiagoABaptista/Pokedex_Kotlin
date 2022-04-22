package com.example.pokedex_kotlin.model.database

import androidx.annotation.WorkerThread
import com.example.pokedex_kotlin.model.entities.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDao: PokemonDao) {

    @WorkerThread
    suspend fun insertPokemonData(pokemon: Pokemon){
        pokemonDao.insertPokemonDetails(pokemon)
    }
    fun filteredListPokemons(value : String) : Flow<List<Pokemon>> =
        pokemonDao.getFilteredPokemonsList(value)

}