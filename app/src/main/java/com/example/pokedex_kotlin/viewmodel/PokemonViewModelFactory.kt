package com.example.pokedex_kotlin.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pokedex_kotlin.network.PokemonRepository

class PokemonViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: PokemonRepository,
    defaultArgs: Bundle? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PokemonViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
/*
class PokemonViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: PokemonRepository,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PokemonViewModel(repository) as T
    }
}*/
