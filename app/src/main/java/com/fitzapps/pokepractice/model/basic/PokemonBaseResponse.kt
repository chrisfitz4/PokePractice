package com.fitzapps.pokepractice.model.basic

data class PokemonBaseResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)