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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_kotlin.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory
import java.io.IOException

class PokemonDetailsFragment : Fragment() {
    private var mBinding : FragmentPokemonDetailsBinding? = null
    private var mPokemonDetails :Pokemon? = null

    private val mPokemonViewModel: PokemonViewModel by viewModels {
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
        Log.i("Teste", "Details 1")
        val args: PokemonDetailsFragmentArgs by navArgs()
        Log.i("Teste", "Details 2")
        args.let {
            try {
                this.mPokemonDetails = args.pokemonsDetails
                Log.i("Teste", "details 3")
                Glide.with(requireActivity())
                    .load(it.pokemonsDetails.imageurl)
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
                                Log.i("Teste", "details 4")
                                mBinding!!.rlDishDetailMain.setBackgroundColor( ContextCompat.getColor(
                                    requireContext(),
                                    colorTypeByID[this@PokemonDetailsFragment.mPokemonDetails!!.typeofpokemon[0]] ?: 0))
                            }
                            return false

                        }

                    })
                    .into(mBinding!!.ivPokemonImage)
                Log.i("Teste", "details 5")
            }catch (e : IOException){
                e.printStackTrace()
            }
            Log.i("Teste", "4")
            mBinding!!.tvPokemonName.text =it.pokemonsDetails.name
            mBinding!!.tvPokemonTypeofpokemon.text = it.pokemonsDetails.typeofpokemon.toString()
            mBinding!!.tvPokemonCategory.text =it.pokemonsDetails.category
            mBinding!!.tvPokemonXDescription.text =it.pokemonsDetails.xDescription
        }
    }
}