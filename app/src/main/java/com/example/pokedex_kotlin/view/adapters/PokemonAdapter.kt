package com.example.pokedex_kotlin.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.ItemHomePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.view.fragments.AllPokemonsFragment

class PokemonAdapter(
    private val fragment : Fragment
) : RecyclerView.Adapter<PokemonViewHolder>() {

    private var pokemons: List<Pokemon> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val binding : ItemHomePokemonBinding =
            ItemHomePokemonBinding.inflate(LayoutInflater.from(fragment.context),
                parent, false)

        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        val pokemon = pokemons[position]
        holder.namePokemon.text = pokemon.name
        holder.idPokemon.text = "${pokemon.id} "
        Glide.with(fragment)
            .load(pokemon.imageurl)
            .into(holder.imagePokemon)

        holder.cardPokemon.setCardBackgroundColor(setPokemonBackgoundColor(pokemon))
        holder.namePokemon.setTextColor(ContextCompat.getColor(fragment.requireContext(),
            R.color.white))
        holder.idPokemon.setTextColor(ContextCompat.getColor(fragment.requireContext(),
            R.color.white))
        holder.itemView.setOnClickListener {
            if(fragment is AllPokemonsFragment){
                fragment.pokemonDetails(pokemon)
            }
        }
        /*
        itemView.constraint_item_indice.setOnClickListener {
            println(pokemon.gifUrl)
            launchDetailScreen(pokemon)
        }
        */
    }
    fun setPokemonBackgoundColor(pokemon: Pokemon): Int {
        return ContextCompat.getColor(
            fragment.requireContext(),
            colorTypeByID[pokemon.typeofpokemon[0]] ?: 0)
    }

    fun addPokemons(newPokemons: List<Pokemon>) {
        pokemons = newPokemons
        notifyDataSetChanged()
    }
}

class PokemonViewHolder (view: ItemHomePokemonBinding)
    : RecyclerView.ViewHolder(view.root){

    val namePokemon = view.tvNamePokemon
    val imagePokemon = view.ivIndicePokemon
    val idPokemon = view.tvIdPokemon
    val cardPokemon = view.cardItemIndicePokemon
}