package com.udg.pokemonapi.ui.data.repository

import com.udg.pokemonapi.ui.data.api.Api
import com.udg.pokemonapi.ui.di.modules.NetworkModule

class PokemonRepository(private val api: Api = NetworkModule.provideApi()) : BaseRepository() {

    suspend fun fetchPokemons(limit: Int) = safeApiCall { api.getPokemons(limit) }
}