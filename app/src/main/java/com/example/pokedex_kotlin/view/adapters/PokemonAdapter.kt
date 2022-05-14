package com.example.pokedex_kotlin.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.ItemHomePokemonBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.colorTypeByID

class PokemonAdapter(private val fragment: Fragment) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    private var pokemons: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemHomePokemonBinding =
            ItemHomePokemonBinding.inflate(LayoutInflater.from(fragment.context),parent,false)
        return ViewHolder(binding)
    }
    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomePokemonBinding =
            ItemHomePokemonBinding.inflate(LayoutInflater.from(fragment.context),parent,false)
        return ViewHolder(binding)
    }*/

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
        private val namePokemon : TextView = view.root.findViewById(R.id.tv_name_pokemon)
        private val imagePokemon : ImageView = view.root.findViewById(R.id.iv_indice_pokemon)
        private val idPokemon: TextView = view.root.findViewById(R.id.tv_id_pokemon)
        private  val cardPokemon : CardView = view.root.findViewById(R.id.card_item_indice_pokemon)


        fun bind(pokemon: Pokemon) {

            namePokemon.text = pokemon.name
            idPokemon.text = "${pokemon.id} "
            Glide.with(itemView.context).load(pokemon.imageSource).into(imagePokemon)

            cardPokemon.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    colorTypeByID.get(pokemon.typeOfPokemon[0]) ?: 0
                )
            )
            namePokemon.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            itemView.setOnClickListener {
                println(pokemon.gifUrl)
                launchDetailScreen(pokemon)
            }
        }
        private fun launchDetailScreen( pokemon :Pokemon){
//            val intent = Intent(itemView.context, PokemonDetailActivity::class.java)
//            intent.putExtra(EXTRA_POKEMON,pokemon)
//            itemView.context.startActivity(intent
        }
    }
}