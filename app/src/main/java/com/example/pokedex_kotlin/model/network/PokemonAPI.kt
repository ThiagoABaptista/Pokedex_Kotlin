package com.example.pokedex_kotlin.model.network

import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {
    @GET(Constants.API_ENDPOINT)
    fun getRandomPokemons() : Single<List<Pokemon>>
}