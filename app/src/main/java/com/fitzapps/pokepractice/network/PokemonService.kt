package com.fitzapps.pokepractice.network

import androidx.lifecycle.LiveData
import com.fitzapps.pokepractice.model.basic.PokemonBaseResponse
import com.fitzapps.pokepractice.model.detailed.PokemonDetailedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon-species/{pathId}/")
    suspend fun getPokemon(@Path("pathId") idNum: Int) : Deferred<PokemonDetailedResponse>

    @GET("pokemon-species/")
    suspend fun getAllPokemon() : LiveData<PokemonBaseResponse>
}