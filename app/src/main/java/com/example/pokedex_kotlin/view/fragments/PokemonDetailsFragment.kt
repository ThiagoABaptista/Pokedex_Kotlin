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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.FragmentPokemonDetailsBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.utils.SetPokemonColors
import com.example.pokedex_kotlin.utils.buildAdapter
import com.example.pokedex_kotlin.utils.colorTypeByID
import com.example.pokedex_kotlin.view.adapters.PokemonEvolutionsAdapter
import com.example.pokedex_kotlin.view.adapters.PokemonTypesAdapter
import com.example.pokedex_kotlin.view.adapters.PokemonWeaknessAdapter
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
                //Glide.with(this).load(pokemon.imageurl).into(mBinding!!.ivPokemonImage)
                Glide.with(requireActivity())
                    .load(pokemon.gifUrl)
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
                                mBinding!!.tvPokemonName.setTextColor(SetPokemonColors(this@PokemonDetailsFragment)
                                    .setPokemonBackgoundColor(pokemon))
                                mBinding!!.tvPokemonCategory.setTextColor(SetPokemonColors(this@PokemonDetailsFragment)
                                    .setPokemonTextColor(pokemon))
                                /*
                                var color = SetPokemonColors(this@PokemonDetailsFragment)
                                    .setPokemonTextColor(pokemon)
                                mBinding!!.cvCardDescription.setCardBackgroundColor(color)
                                mBinding!!.tvDetailsDescription.setBackgroundColor(SetPokemonColors(this@PokemonDetailsFragment)
                                    .setPokemonBackgoundColor(pokemon))
                                mBinding!!.tvLabelXDescription.setTextColor(color)

                                mBinding!!.tvPokemonCategory.setTextColor(color)
                                mBinding!!.tvLabelPokemonCategory.setTextColor(color)
                                mBinding!!.tvPokemonEvolutionsLabel.setTextColor(color)
                                mBinding!!.tvPokemonTypeofpokemon.setTextColor(color)
                                mBinding!!.tvPokemonWeaknessesLabel.setTextColor(color)
                                */
                                /*
                                mBinding!!.rlDishDetailMain.setBackgroundColor(
                                    SetPokemonColors(this@PokemonDetailsFragment)
                                        .setPokemonBackgoundColor(pokemon))
                                */
                                buildAdapter(
                                    mBinding!!.rvPokemonTypeofpokemon,
                                    PokemonTypesAdapter(this@PokemonDetailsFragment,pokemon),
                                    null,
                                    LinearLayoutManager(context)
                                )
                                buildAdapter(
                                    mBinding!!.rvDetailsWeaknesses,
                                    PokemonWeaknessAdapter(this@PokemonDetailsFragment,pokemon),
                                    null,
                                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                                )
                                buildAdapter(
                                    mBinding!!.rvDetailsEvolutions,
                                    PokemonEvolutionsAdapter(this@PokemonDetailsFragment,pokemon),
                                    null,
                                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                                )
                            }
                            return false
                        }

                    })
                    .into(mBinding!!.ivPokemonImage)
            }catch (e : IOException){
                e.printStackTrace()
            }
            mBinding!!.tvDetailsDescription.text = pokemon.xdescription
            mBinding!!.tvPokemonName.text =pokemon.name
            mBinding!!.tvPokemonCategory.text = pokemon.category
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}