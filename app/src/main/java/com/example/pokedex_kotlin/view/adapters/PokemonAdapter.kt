package com.example.pokedex_kotlin.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_kotlin.databinding.ItemHomePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon

class PokemonAdapter(private val fragment: Fragment) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    private var pokemons: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomePokemonBinding =
            ItemHomePokemonBinding.inflate(LayoutInflater.from(fragment.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun pokemonsList(list: List<Pokemon>){
        pokemons = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: ItemHomePokemonBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val ivDishImage = view.ivIndicePokemon
        val tvTitle = view.tvNamePokemon
        val tbId = view.tvIdPokemon
    }
}