package com.udg.pokemonapi.ui.data.local.models

import com.squareup.moshi.Json

data class Pokemon(
    @field:Json(name = "name") val namePokemon: String,
    @field:Json(name = "url") val image: String
)