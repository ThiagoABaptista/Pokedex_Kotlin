package com.example.pokedex_kotlin.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_kotlin.databinding.FragmentAllBinding
import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.network.PokemonRepository
import com.example.pokedex_kotlin.view.activities.MainActivity
import com.example.pokedex_kotlin.view.adapters.PokemonAdapter
import com.example.pokedex_kotlin.viewmodel.PokemonViewModel
import com.example.pokedex_kotlin.viewmodel.PokemonViewModelFactory
import com.example.pokedexagoravai.extension.setVisible

class AllPokemonsFragment : Fragment() {

    private lateinit var mBinding: FragmentAllBinding

    private lateinit var mPokemonAdapter: PokemonAdapter

    private val mPokemonViewModel: PokemonViewModel by activityViewModels(){
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
        mBinding.etMainSearch.setText("")
        mPokemonAdapter = PokemonAdapter(this@AllPokemonsFragment)
        registerObservers()
        configAdapter()
        mPokemonViewModel.getPokemons()
        setSearchPokemon()
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
        mPokemonViewModel.select(pokemon)
        findNavController()
            .navigate(AllPokemonsFragmentDirections.actionNavigationPokemonsAllToNavigationPokemonDetails())
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
    private fun setSearchPokemon() {
        mBinding.etMainSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPokemonViewModel.getPokemonByNameOrId(mBinding.etMainSearch.text.toString())
                    ?.let { mPokemonAdapter.addPokemons(it) }
            }
        })
    }
}