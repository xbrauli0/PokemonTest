package com.udg.pokemonapi.ui.data.api

sealed class ResultWrapper<out T> {
    data class Success<out T>(val response: T?) : ResultWrapper<T>()

    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}

data class ErrorResponse(
    var msg: String = ""
)