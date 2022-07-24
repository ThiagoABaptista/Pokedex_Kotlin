package com.example.pokedex_kotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.FragmentFavoritesBinding
import com.example.pokedex_kotlin.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by activityViewModels()
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val textView: TextView = binding.tvNoFavoritePokemonsAddedYet
        favoritesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = getString(R.string.label_no_favorite_pokemons_added_yet)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}