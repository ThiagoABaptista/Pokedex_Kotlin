package com.example.pokedex_kotlin.application

import android.app.Application
import com.example.pokedex_kotlin.model.database.PokemonRoomDatabase
import com.example.pokedex_kotlin.network.PokemonRepository

//A application class
class PokemonApplication : Application() {

    private val database by lazy { PokemonRoomDatabase.getDatabase(this@PokemonApplication) }

    val repository by lazy { PokemonRepository(database.pokemonDao()) }

}