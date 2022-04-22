package com.example.pokedex_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex_kotlin.model.database.PokemonRepository
import com.example.pokedex_kotlin.model.entities.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository:PokemonRepository) : ViewModel(){

    fun insert(pokemon: Pokemon) = viewModelScope.launch{
        repository.insertPokemonData(pokemon)
    }

    fun getFilteredList(value : String) : LiveData<List<Pokemon>> =
        repository.filteredListPokemons(value).asLiveData()

}