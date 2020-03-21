package com.fitzapps.pokepractice.viewModel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PokemonViewModelTest {

    lateinit var viewModel: PokemonViewModel

    @Before
    fun setUp(){
        viewModel = PokemonViewModel()
    }

    @Test
    fun testAllApiCall() {
        val allPokemonResponse = viewModel.getAllPokemon().blockingFirst()
        assertNotNull(allPokemonResponse)
        assert(allPokemonResponse.results.isNotEmpty())
        assert(!allPokemonResponse.results[0].name.isBlank())

        val url = allPokemonResponse.results[0].url
        val split = url.split("/")
        val last = split[split.lastIndex-1]
        val id = last.toInt()
        val aPokemonResponse = viewModel.getAPokemon(id).blockingFirst()

        assertNotNull(aPokemonResponse)
        assertEquals(allPokemonResponse.results[0].name,aPokemonResponse.name)
        assertEquals("bulbasaur", allPokemonResponse.results[0].name)
    }

    @Test
    fun testGetAllPokemon(){
        val allPokemonResponse = viewModel.getAllPokemon().blockingFirst()
        assertNotNull(allPokemonResponse)
        assert(allPokemonResponse.results.isNotEmpty())
        assert(!allPokemonResponse.results[0].name.isBlank())
    }
}