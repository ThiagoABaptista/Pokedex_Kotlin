package com.example.pokedex_kotlin.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.model.entities.Pokemon
import java.lang.IndexOutOfBoundsException
//This Class is just so that the functions to set color can be globally used
class SetPokemonColors (
        private val fragment : Fragment
        ){
    fun setPokemonBackgoundColor(pokemon: Pokemon): Int {
        return ContextCompat.getColor(
            fragment.requireContext(),
            colorTypeByID[pokemon.typeofpokemon[0]] ?: 0)
    }
    fun setPokemonTextColor(pokemon: Pokemon): Int {
        try {
            return ContextCompat.getColor(
                fragment.requireContext(),
                colorTypeByID[pokemon.typeofpokemon[1]]!!)
        }catch (e : IndexOutOfBoundsException){
            if(pokemon.typeofpokemon.contains("Ice")){
                return ContextCompat.getColor(fragment.requireContext(), R.color.colorPrimary);
            }
            return ContextCompat.getColor(
                fragment.requireContext(),
                R.color.white)
        }
    }
}