package com.example.pokedex_kotlin.view.fragmensts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex_kotlin.R
import com.example.pokedex_kotlin.databinding.FragmentFavoritesBinding
import com.example.pokedex_kotlin.viewmodel.FavoritesViewModel

class FragmentFavorites : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvNoFavoritePokemonsAddedYet
        favoritesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = getString(R.string.label_no_favorite_pokemons_added_yet)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}