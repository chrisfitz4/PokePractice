package com.fitzapps.pokepractice.network

import com.fitzapps.pokepractice.model.basic.PokemonBaseResponse
import com.fitzapps.pokepractice.model.detailed.PokemonDetailedResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon-species/{pathId}/")
    fun getPokemon(@Path("pathId") idNum: Int) : Observable<PokemonDetailedResponse>

    @GET("pokemon-species/")
    fun getAllPokemon() : Observable<PokemonBaseResponse>
}