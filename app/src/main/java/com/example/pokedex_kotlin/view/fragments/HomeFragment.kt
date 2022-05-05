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
import com.example.pokedex_kotlin.databinding.FragmentHomeBinding
import com.example.pokedex_kotlin.view.adapters.PokemonAdapter
import com.example.pokedex_kotlin.viewmodel.HomeViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding

    private lateinit var mPokemonAdapter: PokemonAdapter

    private lateinit var mCustomListDialog: Dialog

    private val mPokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory((requireActivity().application as PokemonApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rvPokemonListAll.layoutManager = GridLayoutManager(requireActivity(),1)
        mPokemonAdapter = PokemonAdapter(this@HomeFragment)
        mBinding.rvPokemonListAll.adapter = mPokemonAdapter

        mPokemonViewModel.allPokemonsList.observe(viewLifecycleOwner) {
            pokemons ->
            pokemons.let {
                if(it.isNotEmpty()){

                }
            }
        }

    }
}