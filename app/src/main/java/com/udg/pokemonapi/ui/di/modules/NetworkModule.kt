package com.udg.pokemonapi.ui.di.modules

import com.udg.pokemonapi.BuildConfig
import com.udg.pokemonapi.ui.data.api.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {

    fun provideApi(retrofit: Retrofit = provideRetrofit()): Api = retrofit.create(Api::class.java)

    fun provideRetrofit(okHttpClient: OkHttpClient = provideOkHttpClient()): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}
