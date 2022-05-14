package com.example.pokedex_kotlin.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pokedex_kotlin.model.database.PokemonRepository
import com.example.pokedex_kotlin.model.network.PokemonApiService
import java.lang.IllegalArgumentException

class PokemonViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: PokemonApiService,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PokemonViewModel(repository) as T
    }

}