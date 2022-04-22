package com.example.pokedex_kotlin.model.network

import com.example.pokedex_kotlin.model.entities.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {
    @GET("pokemon.json")
    suspend fun getPokemons() : Response<List<Pokemon>>
}