package com.example.pokedex_kotlin.viewmodel

import androidx.lifecycle.*
import com.example.pokedex_kotlin.model.database.PokemonRepository
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.model.network.PokemonApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository:PokemonApiService) : ViewModel(){

    private val pokemonApiService = PokemonApiService()

    private val compositeDisposable = CompositeDisposable()

    val loadPokemons = MutableLiveData<Boolean>()
    val pokemonResponse = MutableLiveData<List<Pokemon>>()
    val pokemonLoadingError = MutableLiveData<Boolean>()
    fun getPokemonsFromAPI(){
        loadPokemons.value = true
        compositeDisposable.add(
            pokemonApiService.getPokemons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<List<Pokemon>>(){
                    fun onSuccess(value: List<Pokemon>) {

                    }

                    override fun onError(e: Throwable) {
                        loadPokemons.value = false
                        pokemonLoadingError.value = true
                        e!!.printStackTrace()
                    }

                    override fun onNext(t: List<Pokemon>) {
                        pokemonResponse.postValue(t)
                    }

                    override fun onComplete() {
                        loadPokemons.value = false
                        pokemonLoadingError.value = false
                    }

                })
        )
    }
    /*fun insert(pokemon: Pokemon) = viewModelScope.launch{
        repository.insertPokemonData(pokemon)
    }


    fun getFilteredList(value : String) : LiveData<List<Pokemon>> =
        repository.filteredListPokemons(value).asLiveData()

    val allPokemonsList: LiveData<List<Pokemon>> = repository.allPokemonsList.asLiveData()*/
}