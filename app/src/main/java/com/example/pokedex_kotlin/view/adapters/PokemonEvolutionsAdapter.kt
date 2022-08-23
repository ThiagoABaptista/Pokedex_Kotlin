package com.example.pokedex_kotlin.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex_kotlin.databinding.ItemDetailsImageEvolutionsPokemonBinding
import com.example.pokedex_kotlin.databinding.ItemDetailsTypePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.utils.drawableTypeByID

class PokemonEvolutionsAdapter(
    private val fragment : Fragment,
    private val pokemon: Pokemon
): RecyclerView.Adapter<PokemonEvolutionsItemViewHolder>() {
    val evolutions = pokemon.evolutions
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonEvolutionsItemViewHolder {
        val binding : ItemDetailsImageEvolutionsPokemonBinding =
            ItemDetailsImageEvolutionsPokemonBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false)
        return PokemonEvolutionsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonEvolutionsItemViewHolder, position: Int) {
        val evolutionId = evolutions[position].replace("#","")
        val url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$evolutionId.png"
        Glide.with(fragment.requireContext())
            .load(url)
            .into(holder.imageEvolution)
        holder.imageEvolution.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = evolutions.size
}

class PokemonEvolutionsItemViewHolder (itemView : ItemDetailsImageEvolutionsPokemonBinding) : RecyclerView.ViewHolder(itemView.root){
    val imageEvolution: ImageView = itemView.ivItemImageEvolution
}