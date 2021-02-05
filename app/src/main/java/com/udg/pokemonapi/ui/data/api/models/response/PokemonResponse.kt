package com.udg.pokemonapi.ui.data.api.models.response

import com.squareup.moshi.Json
import com.udg.pokemonapi.ui.data.local.models.Pokemon

data class PokemonResponse(
    @field:Json(name = "results") val results: List<Pokemon>
)