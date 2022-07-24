package com.example.pokedex_kotlin.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory
import java.io.IOException
import java.lang.IndexOutOfBoundsException
import kotlin.math.log

class PokemonDetailsFragment : Fragment() {
    private var mBinding : FragmentPokemonDetailsBinding? = null
    private var mPokemonDetails :Pokemon? = null

    private val mPokemonViewModel: PokemonViewModel by activityViewModels() {
        PokemonViewModelFactory(this, PokemonRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       mBinding = FragmentPokemonDetailsBinding.inflate(inflater, container,false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPokemonViewModel.selected.observe(viewLifecycleOwner,Observer<Pokemon>{
            pokemon ->
            try {
                this.mPokemonDetails = pokemon
                Glide.with(requireActivity())
                    .load(pokemon.imageurl)
                    .centerCrop()
                    .listener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("TAG","ERROR Loading the image",e)
                            return false
                        }
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource.let {
                                var color : Int
                                try {
                                    color = ContextCompat.getColor(
                                        requireContext(),
                                        colorTypeByID[pokemon.typeofpokemon[1]]!!)
                                }catch (e : IndexOutOfBoundsException){
                                    if(pokemon.typeofpokemon.contains("Ice")){
                                        color = ContextCompat.getColor(requireContext(),R.color.colorPrimary);
                                    }else{
                                        color = ContextCompat.getColor(
                                            requireContext(),
                                            R.color.white)
                                    }
                                }
                                mBinding!!.tvPokemonName.setTextColor(color)
                                mBinding!!.tvPokemonCategory.setTextColor(color)
                                mBinding!!.tvPokemonTypeofpokemon.setTextColor(color)
                                mBinding!!.tvPokemonXDescription.setTextColor(color)
                                mBinding!!.tvPokemonEvolutionsLabel.setTextColor(color)
                                mBinding!!.tvPokemonWeaknessesLabel.setTextColor(color)
                                mBinding!!.rlDishDetailMain.setBackgroundColor( ContextCompat.getColor(
                                    requireContext(),
                                    colorTypeByID[this@PokemonDetailsFragment.mPokemonDetails!!.typeofpokemon[0]] ?: 0))
                            }
                            return false
                        }

                    })
                    .into(mBinding!!.ivPokemonImage)
            }catch (e : IOException){
                e.printStackTrace()
            }
            mBinding!!.tvPokemonName.text =pokemon.name
            mBinding!!.tvPokemonTypeofpokemon.text = pokemon.typeofpokemon.toString()
            mBinding!!.tvPokemonCategory.text =pokemon.category
            mBinding!!.tvPokemonXDescription.text = pokemon.xdescription
        })
    }
}