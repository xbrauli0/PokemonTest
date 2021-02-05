package com.udg.pokemonapi.ui.pokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udg.pokemonapi.ui.data.api.ErrorResponse
import com.udg.pokemonapi.ui.data.api.ResultWrapper
import com.udg.pokemonapi.ui.data.api.models.response.PokemonResponse
import com.udg.pokemonapi.ui.data.local.models.Pokemon
import com.udg.pokemonapi.ui.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository = PokemonRepository()) : ViewModel() {

    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons

    private val _pokemons = MutableLiveData<List<Pokemon>>()

    fun fetchPokemons(limit: Int) {
        viewModelScope.launch {
            when(val result = repository.fetchPokemons(limit)) {
                is ResultWrapper.Success -> handlePokemonResponse(result.response)
                is ResultWrapper.GenericError -> notifyErrorToUser(result.code, result.error)
                is ResultWrapper.NetworkError -> notifyAboutNetworkError()
            }
        }
    }

    private fun handlePokemonResponse(response: PokemonResponse?) {
       if (response != null) {
           _pokemons.value = response.results
       }
    }

    private fun notifyErrorToUser(code: Int?, error: ErrorResponse?) {
        Log.e("ERROR", "$code ${error.toString()}")
    }

    private fun notifyAboutNetworkError() {
        Log.e("ERROR", "notifyAboutNetworkError()")
    }
}