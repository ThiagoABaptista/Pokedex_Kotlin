package com.example.pokedex_kotlin.model.network

import com.example.pokedex_kotlin.model.entities.Pokemon
import com.example.pokedex_kotlin.utils.Constants
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PokemonApiService {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(setLoggingLevel())
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun setLoggingLevel(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
    private val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonAPI::class.java)
    fun getPokemons(): Observable<List<Pokemon>>{
        return api.getPokemons()
    }
}