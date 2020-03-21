package com.fitzapps.pokepractice.view.dataBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitzapps.pokepractice.model.PokemonInfo
import com.fitzapps.pokepractice.view.adapter.PokemonRVAdapter


@BindingAdapter("pokemonList")
fun updateAdapter(view: RecyclerView, pokemonInfoList: List<PokemonInfo>?) {
    (view.adapter as PokemonRVAdapter?)?.apply {
        pokemonInfo = pokemonInfoList
        notifyDataSetChanged()
    }
}

@BindingAdapter("pokemonAdapter")
fun setAdapter(view: RecyclerView, pokemonRVAdapter: PokemonRVAdapter?) {
    view.adapter = pokemonRVAdapter
}

