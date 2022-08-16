package com.example.pokedex_kotlin.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.ItemDetailsTypePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.SetPokemonColors
import com.example.pokedex_kotlin.utils.colorTypeByID

class PokemonTypesAdapter (
    private val fragment : Fragment,
    private val pokemon: Pokemon
) :RecyclerView.Adapter<PokemonTypesViewHolder>(){
    private val types = pokemon.typeofpokemon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypesViewHolder {
        val binding : ItemDetailsTypePokemonBinding = ItemDetailsTypePokemonBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false)
        return PokemonTypesViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypesViewHolder, position: Int) {
        val type = types[position]

        holder.typeText.text = types[position]
        Log.i("Tese Types holder", holder.typeText.text as String)
        Log.i("Tese Types array", types[position])
        holder.bind(type)
        holder.typeText.setTextColor(ContextCompat.getColor(fragment.requireContext(),
            colorTypeByID.get(type)?:0))
        /*
        val setPokemonsColors = SetPokemonColors(fragment)
        holder.typeText.setTextColor(setPokemonsColors.setPokemonTextColor(pokemon))
        */
    }
}

class PokemonTypesViewHolder (itemView : ItemDetailsTypePokemonBinding) :RecyclerView.ViewHolder(itemView.root){
    val typeText: TextView = itemView.tvItemDetailsType
    fun bind(type:String){
        typeText.text = type
        typeText.setTextColor(ContextCompat.getColor(itemView.context, colorTypeByID.get(type)?:0))
    }
}