package com.fitzapps.pokepractice.viewModel

import androidx.lifecycle.*
import com.fitzapps.pokepractice.model.PokemonInfo
import com.fitzapps.pokepractice.model.basic.PokemonBaseResponse
import com.fitzapps.pokepractice.network.PokemonRetrofit

//dataBinding and load image
class PokemonViewModel : ViewModel() {
    private val myUrl = "https://img.pokemondb.net/artwork/large/bulbasaur.jpg"
    private val pokemonService = PokemonRetrofit().createService()

    val pokemonInfo : LiveData<List<PokemonInfo>> = MutableLiveData<List<PokemonInfo>>()
    private val pokemonMediatorLiveData = MediatorLiveData<PokemonInfo>()


    suspend fun getAllPokemon() = pokemonService.getAllPokemon()
    suspend fun getAPokemon(id: Int) = pokemonService.getPokemon(id)

    private suspend fun allPokemon(): List<PokemonInfo> {
        val allPokemon: LiveData<PokemonBaseResponse> = getAllPokemon()
        //convert from PokemonBaseResponse to List<Result>
        val listResults = Transformations.map(allPokemon){baseResponse->
            baseResponse.results
        }
        //get the List<Int> of ids
        val listIds = Transformations.map(listResults) { inputList ->
            inputList.map{input->
                getNumberForPath(input.url)

            }
        }
        //get the PokemonInfo for each id
        Transformations.map(listIds) { inputId: List<Int>->
            for(id in inputId){
                
            }
        }
    }

    private suspend fun aPokemon(id: Int) : PokemonInfo {
        val response = getAPokemon(id).await()
        return PokemonInfo(response.name, response.color.name, response.shape.name, myUrl)
    }

    private fun getNumberForPath(url: String): Int {
        val split = url.split("/")
        val last = split[split.lastIndex - 1]
        return last.toInt()
    }

}