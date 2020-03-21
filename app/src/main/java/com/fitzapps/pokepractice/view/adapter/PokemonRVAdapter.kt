package com.fitzapps.pokepractice.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitzapps.pokepractice.R
import com.fitzapps.pokepractice.model.PokemonInfo
import kotlinx.android.synthetic.main.item_layout.view.*

class PokemonRVAdapter : RecyclerView.Adapter<PokemonRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.pokemonName
        val color: TextView = itemView.pokemonColor
        val shape: TextView = itemView.pokemonShape
        val image: ImageView = itemView.pokemonImage
    }

    var pokemonInfo: List<PokemonInfo>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonInfo?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pokemonInfo?.get(position)?.let{pokemonInfoResult->
            holder.name.text = pokemonInfoResult.name
            holder.color.text = pokemonInfoResult.color
            holder.shape.text = pokemonInfoResult.shape
            Glide.with(holder.itemView.context).load(pokemonInfoResult.image).into(holder.image)
        }
    }


}