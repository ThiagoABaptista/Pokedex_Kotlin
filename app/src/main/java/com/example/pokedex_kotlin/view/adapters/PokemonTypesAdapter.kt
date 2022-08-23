package com.example.pokedex_kotlin.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_kotlin.databinding.ItemDetailsTypePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.SetPokemonColors
import com.example.pokedex_kotlin.utils.colorTypeByID

class PokemonTypesAdapter (
    private val fragment : Fragment,
    private val pokemon: Pokemon
) :RecyclerView.Adapter<PokemonTypesItemViewHolder>(){
    private val types = pokemon.typeofpokemon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypesItemViewHolder {
        val binding : ItemDetailsTypePokemonBinding = ItemDetailsTypePokemonBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false)
        return PokemonTypesItemViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypesItemViewHolder, position: Int) {
        val type = types[position]

        holder.typeText.text = types[position]
        if(SetPokemonColors(fragment).setPokemonTextColor(pokemon) == colorTypeByID.get(type)){
            holder.typeText.setShadowLayer(
                2F, 0F, 0F,
                SetPokemonColors(fragment).setPokemonTextColor(pokemon)
            )
        }

        holder.typeText.setTextColor(ContextCompat.getColor(fragment.requireContext(),
            colorTypeByID.get(type)?:0))
        /*
        val setPokemonsColors = SetPokemonColors(fragment)
        holder.typeText.setTextColor(setPokemonsColors.setPokemonTextColor(pokemon))
        */
    }
}

class PokemonTypesItemViewHolder (itemView : ItemDetailsTypePokemonBinding) :RecyclerView.ViewHolder(itemView.root){
    val typeText: TextView = itemView.tvItemDetailsType
}