package com.udg.pokemonapi.ui.data.repository

import com.squareup.moshi.Moshi
import com.udg.pokemonapi.ui.data.api.ErrorResponse
import com.udg.pokemonapi.ui.data.api.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected suspend fun <R> safeApiCall(apiCall: suspend () -> Response<R>): ResultWrapper<R> =
        withContext(Dispatchers.IO) {
            try {
                val httpResponse = apiCall.invoke()
                if (httpResponse.isSuccessful) {
                    ResultWrapper.Success(httpResponse.body())
                } else {
                    ResultWrapper.GenericError(httpResponse.code())
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
            }
        }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}