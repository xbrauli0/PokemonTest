package com.udg.pokemonapi.ui.data.api

import com.udg.pokemonapi.ui.data.api.models.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int): Response<PokemonResponse>
}