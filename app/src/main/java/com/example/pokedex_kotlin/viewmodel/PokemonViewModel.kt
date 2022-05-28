package com.example.pokedex_kotlin.viewmodel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pokedex_kotlin.model.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedexagoravai.extension.launchSafe
import com.example.pokedexagoravai.util.Status
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class PokemonViewModel(var repository: PokemonRepository) : ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons

    private val _pokemonsNotFound = MutableLiveData<Boolean>()
    val pokemonsNotFound: LiveData<Boolean>
        get() = _pokemonsNotFound

    fun getPokemons() {
        viewModelScope.launchSafe {
            repository.getPokemons { result ->
                when (result.status) {
                    Status.SUCCESS -> _pokemons.postValue(result.data!!)
                    else -> _pokemonsNotFound.postValue(true)
                }
            }
        }
    }

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
    }
}



    /*fun insert(pokemon: Pokemon) = viewModelScope.launch{
        repository.insertPokemonData(pokemon)
    }


    fun getFilteredList(value : String) : LiveData<List<Pokemon>> =
        repository.filteredListPokemons(value).asLiveData()

    val allPokemonsList: LiveData<List<Pokemon>> = repository.allPokemonsList.asLiveData()*/