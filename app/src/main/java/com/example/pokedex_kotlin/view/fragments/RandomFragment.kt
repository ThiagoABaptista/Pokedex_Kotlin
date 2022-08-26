package com.example.pokedex_kotlin.view.fragments

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_kotlin.databinding.FragmentRandomBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.view.adapters.AllPokemonAdapter
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory
import kotlin.random.Random

class RandomFragment : Fragment() {

    private lateinit var mBinding: FragmentRandomBinding

    private lateinit var mPokemonAdapter: AllPokemonAdapter

    private val mPokemonViewModel: PokemonViewModel by activityViewModels(){
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
    ): View? {
        mBinding =
            FragmentRandomBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pokemons_list : List<Pokemon>
        mPokemonViewModel.pokemons.observe(viewLifecycleOwner){
                pokemons ->
            pokemons_list = pokemons
            val random = Random.nextInt(0,pokemons_list.size)
            Log.i("Teste 2", random.toString())
            Log.i("Teste 2", pokemons_list[random].toString())
            Glide.with(requireActivity())
                .load(pokemons_list[random].imageurl)
                .centerCrop()
                .listener(object: RequestListener<Drawable>{
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
                            val matrix = ColorMatrix()
                            matrix.setSaturation(0F)
                            val colorFilter = ColorMatrixColorFilter(matrix)
                            mBinding.ivPokemonRandomImage.colorFilter = colorFilter
                        }
                        return false
                    }

                })
                .into(mBinding.ivPokemonRandomImage)

        }
    }
}