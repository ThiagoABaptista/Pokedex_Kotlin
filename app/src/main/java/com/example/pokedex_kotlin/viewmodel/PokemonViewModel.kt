package com.example.pokedex_kotlin.viewmodel

import androidx.lifecycle.*
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedexagoravai.extension.launchSafe
import com.example.pokedexagoravai.util.Status
import java.util.*

class PokemonViewModel(var repository: PokemonRepository) : ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons
    val selected = MutableLiveData<Pokemon>()
    private val _pokemonsNotFound = MutableLiveData<Boolean>()
    val pokemonsNotFound: LiveData<Boolean>
        get() = _pokemonsNotFound

    fun select(pokemon: Pokemon) {
        selected.value = pokemon
    }
    fun getPokemons() {
        viewModelScope.launchSafe {
            repository.getPokemons {
                    result ->
                when (result.status) {
                    Status.SUCCESS -> _pokemons.postValue(result.data!!)
                    else -> _pokemonsNotFound.postValue(true)
                }
            }
        }
    }
/*
    fun getSpecificPokemon(pokemonNameOrId: String = "") {
        _pokemons.value = pokemons.value?.filter { pokemon ->
            pokemon.name.uppercase(Locale.ROOT)
                .contains(pokemonNameOrId.uppercase(Locale.ROOT)) || pokemon.id.contains(
                pokemonNameOrId
            )
        }
        if (_pokemons.value.isNullOrEmpty()) {
            _pokemonsNotFound.value = true
        }
        if (pokemonNameOrId.isBlank()) getPokemons()
    }*/
}



    /*fun insert(pokemon: Pokemon) = viewModelScope.launch{
        repository.insertPokemonData(pokemon)
    }


    fun getFilteredList(value : String) : LiveData<List<Pokemon>> =
        repository.filteredListPokemons(value).asLiveData()

    val allPokemonsList: LiveData<List<Pokemon>> = repository.allPokemonsList.asLiveData()*/