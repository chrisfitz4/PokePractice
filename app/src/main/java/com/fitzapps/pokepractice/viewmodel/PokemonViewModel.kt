package com.fitzapps.pokepractice.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitzapps.pokepractice.model.PokemonInfo
import com.fitzapps.pokepractice.model.basic.Result
import com.fitzapps.pokepractice.network.PokemonRetrofit
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

//dataBinding and load image
class PokemonViewModel : ViewModel() {
    private val pokemonService = PokemonRetrofit().createService()
    val pokemonInfo = MutableLiveData<List<PokemonInfo>>()

    private val myUrl = "https://img.pokemondb.net/artwork/large/bulbasaur.jpg"
    private var disposable : Disposable? = null

    fun getAllPokemon() = pokemonService.getAllPokemon()
    fun getAPokemon(id: Int) = pokemonService.getPokemon(id)

    fun getListOfPokemonInfo() {
        disposable = allPokemon().subscribe({successfulResponse->
            pokemonInfo.postValue(successfulResponse)
            disposable?.dispose()
        }, {error->
            Log.d("TAG_X","onError: ${error.message}")
            disposable?.dispose()
        })


    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    private fun allPokemon(): Single<List<PokemonInfo>> {
        return getAllPokemon().subscribeOn(Schedulers.io())
            //convert from PokemonBaseResponse to List<Result>
            .map { input ->
                Log.d("TAG_X", "map: ")
                input.results
            }
            //convert from Observable<List<Result>> to a list of Observable<Result>
            .flatMapIterable { input: List<Result> ->
                Log.d("TAG_X", "flatMapIterable: ")
                //convert the urls to the the ids
                val ids = input.map {
                    getNumberForPath(it.url)
                }
                ids
            }
            //convert from Observable<Int> to Observable<PokemonInfo>
            .flatMap { id ->
                aPokemon(id)
            }
            //compress the responses back to a single Observable<List<T>>
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun aPokemon(id: Int): Observable<PokemonInfo> {
        return getAPokemon(id).subscribeOn(Schedulers.io())
            .map { input ->
                PokemonInfo(input.name, input.color.name, input.shape.name, myUrl)
            }
    }

    private fun getNumberForPath(url: String): Int {
        val split = url.split("/")
        val last = split[split.lastIndex - 1]
        return last.toInt()
    }

}