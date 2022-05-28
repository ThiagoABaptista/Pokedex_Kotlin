package com.example.pokedex_kotlin.network

import com.example.pokedex_kotlin.model.Pokemon
import com.example.pokedexagoravai.util.LiveDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonRepository{

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

}