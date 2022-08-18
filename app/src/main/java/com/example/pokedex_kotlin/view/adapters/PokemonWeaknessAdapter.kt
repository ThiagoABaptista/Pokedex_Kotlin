package com.example.pokedex_kotlin.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_kotlin.databinding.ItemDetailsWeaknessPokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.utils.drawableTypeByID

class PokemonWeaknessAdapter(
    private val fragment : Fragment,
    private val pokemon: Pokemon
): RecyclerView.Adapter<PokemonWeaknessItemViewHolder>(){
    private val weakness = pokemon.weaknesses
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonWeaknessItemViewHolder {
        val binding : ItemDetailsWeaknessPokemonBinding = ItemDetailsWeaknessPokemonBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false)
        return PokemonWeaknessItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonWeaknessItemViewHolder, position: Int) {
        val typeWeakness = weakness[position]
        holder.imageWeakness.setImageDrawable(
            ContextCompat.getDrawable(
                fragment.requireContext(),
                drawableTypeByID.get(typeWeakness)?:0
            )
        )
    }

    override fun getItemCount(): Int = weakness.size

}
class PokemonWeaknessItemViewHolder(itemView: ItemDetailsWeaknessPokemonBinding): RecyclerView.ViewHolder(itemView.root) {
    val imageWeakness = itemView.ivItemWeakness
}