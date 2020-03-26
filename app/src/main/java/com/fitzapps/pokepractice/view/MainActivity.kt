package com.fitzapps.pokepractice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fitzapps.pokepractice.R
import com.fitzapps.pokepractice.databinding.ActivityMainBinding
import com.fitzapps.pokepractice.view.adapter.PokemonRVAdapter
import com.fitzapps.pokepractice.viewModel.PokemonViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        activityMainBinding.viewModel = viewModel
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.pokemonAdapter = PokemonRVAdapter()

        //viewModel.getListOfPokemonInfo()
    }
}
