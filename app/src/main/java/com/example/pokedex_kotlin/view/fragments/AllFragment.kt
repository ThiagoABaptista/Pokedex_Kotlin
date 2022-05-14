package com.example.pokedex_kotlin.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_kotlin.application.PokemonApplication
import com.example.pokedex_kotlin.databinding.FragmentAllBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.model.network.PokemonApiService
import com.example.pokedex_kotlin.view.adapters.PokemonAdapter
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory

class AllFragment : Fragment() {

    private lateinit var mBinding: FragmentAllBinding

    private lateinit var mPokemonAdapter: PokemonAdapter

    private lateinit var mCustomListDialog: Dialog

    private val mPokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(this,PokemonApiService())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAllBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvPokemonListAll.layoutManager = GridLayoutManager(requireActivity(),1)
        mPokemonAdapter = PokemonAdapter(this@AllFragment)
        mBinding.rvPokemonListAll.adapter = mPokemonAdapter

        mPokemonViewModel.pokemonResponse.observe(viewLifecycleOwner) {
                pokemons ->
            pokemons.let {
                if(it.isNotEmpty()){
                    mBinding.rvPokemonListAll.visibility = View.VISIBLE
                    mBinding.tvNoPokemons.visibility = View.GONE

                    mPokemonAdapter.pokemonsList(it)
                }else{
                    mBinding.rvPokemonListAll.visibility = View.GONE
                    mBinding.tvNoPokemons.visibility = View.VISIBLE
                }
            }
        }

    }
    private fun setPokemonsResponseInUI(pokemons : List<Pokemon>){

    }
}