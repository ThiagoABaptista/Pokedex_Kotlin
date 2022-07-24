package com.example.pokedex_kotlin.application

import android.app.Application
import com.example.pokedex_kotlin.model.database.PokemonRepository
import com.example.pokedex_kotlin.model.database.PokemonRoomDatabase

class PokemonApplication : Application() {

    private val database by lazy {
        PokemonRoomDatabase.getDatabase(this@PokemonApplication)
    }

    val repository by lazy {
        PokemonRepository(database.pokemonDao())
    }

}