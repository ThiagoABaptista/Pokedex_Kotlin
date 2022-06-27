package com.example.pokedex_kotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex_kotlin.databinding.FragmentAllBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.model.entities.PokemonRoomModel
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.view.activities.MainActivity
import com.example.pokedex_kotlin.view.adapters.PokemonAdapter
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory
import com.example.pokedexagoravai.extension.setVisible
import java.lang.Integer.parseInt

class AllPokemonsFragment : Fragment() {

    private lateinit var mBinding: FragmentAllBinding

    private lateinit var mPokemonAdapter: PokemonAdapter

    private val mPokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(this, PokemonRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            FragmentAllBinding.inflate(inflater, container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPokemonAdapter = PokemonAdapter(this@AllPokemonsFragment)
        registerObservers()
        configAdapter()
        mPokemonViewModel.getPokemons()
        //searchPokemon()
    }

    private fun configAdapter(){
        val layoutManager = GridLayoutManager(requireActivity(),1)
        mBinding.rvPokemonListAll.layoutManager = layoutManager
        mBinding.rvPokemonListAll.setHasFixedSize(true)
        mBinding.rvPokemonListAll.adapter = mPokemonAdapter
    }
    private fun registerObservers(){
        mPokemonViewModel.pokemons.observe(viewLifecycleOwner){
            pokemons ->
            pokemons.let {
                if (it.isNotEmpty()){
                    mPokemonAdapter.addPokemons(pokemons)
                    showPokemons(true)
                }else{
                    showPokemons(false)
                }
            }
        }
        /*mPokemonViewModel.pokemons.observe(viewLifecycleOwner, Observer { pokemons ->
            mPokemonAdapter.addPokemons(pokemons ?: return@Observer)
            showPokemons(true)
        })

        mPokemonViewModel.pokemonsNotFound.observe(viewLifecycleOwner, Observer { didntFind->
            if(mBinding.rvPokemonListAll.isVisible && didntFind){
                showPokemons(false)
            }
        })*/
    }
    fun pokemonDetails(pokemon: Pokemon){
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }
        findNavController()
            .navigate(AllPokemonsFragmentDirections.actionNavigationPokemonsAllToNavigationPokemonDetails(pokemon))
    }

    private fun showPokemons(show:Boolean) {
        mBinding.rvPokemonListAll.setVisible(show)
    }
    override fun onResume() {
        super.onResume()

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    /*
    private fun searchPokemon() {
        edit_text_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getSpecificPokemon(edit_text_search.text.toString())
            }
        })
    }
    */
}