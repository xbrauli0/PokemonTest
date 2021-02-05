package com.udg.pokemonapi.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.udg.pokemonapi.R
import com.udg.pokemonapi.ui.data.local.models.Pokemon

class PokemonFragment : Fragment() {

    private lateinit var tv: TextView
    private lateinit var button: Button

    private var limit = 0

    private val viewModel = PokemonViewModel()

    companion object {
        fun newInstance() = PokemonFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pokemons.observe(this, Observer { pokemons ->
            updateList(pokemons)
        })
    }

    private fun updateList(pokemons: List<Pokemon>?) {
        // TODO : Handle pokemons list and display it into a RecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokemon_fragment, container, false)

        tv = view.findViewById(R.id.tv)
        button = view.findViewById(R.id.btn_fetch)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv.text = "Click on button"
        button.setOnClickListener {
            limit++
            viewModel.fetchPokemons(limit)
        }
    }
}