package com.udg.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udg.pokemonapi.ui.main.MainFragment
import com.udg.pokemonapi.ui.pokemon.PokemonFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PokemonFragment.newInstance())
                    .commitNow()
        }
    }
}