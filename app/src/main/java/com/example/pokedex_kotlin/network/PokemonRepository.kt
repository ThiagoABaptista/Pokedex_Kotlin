package com.example.pokedex_kotlin.network

import androidx.annotation.WorkerThread
import com.example.pokedex_kotlin.model.database.PokemonDao
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedexagoravai.util.LiveDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonRepository(private val pokemonDao: PokemonDao) {
    var service = PokeIndexController.retrofit.create(PokemonService::class.java)

    suspend fun getPokemons(listener: (LiveDataResult<List<Pokemon>>) -> Unit) {
        withContext(Dispatchers.IO) {
            try{
                val request = service.getPokemons()
                val response = LiveDataNetworking.request(request)
                listener(response)
            } catch (e: Exception) {
                listener(LiveDataResult.error(e))
            }
        }

    }
    @WorkerThread
    suspend fun insertPokemonData(pokemon: Pokemon){
        pokemonDao.insertPokemonDetails(pokemon)
    }
    @WorkerThread
    suspend fun deletePokemonData(pokemon: Pokemon){
        pokemonDao.deletePokemonDetails(pokemon)
    }

}