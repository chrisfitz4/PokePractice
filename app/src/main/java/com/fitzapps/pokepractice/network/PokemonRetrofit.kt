package com.fitzapps.pokepractice.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRetrofit {

    private val BASE_URL = "https://pokeapi.co/api/v2/"

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideHttpClient())
            .build()
    }

    private fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(provideHttpInterceptor()).build()
    }

    private fun provideService(retrofit: Retrofit) : PokemonService{
        return retrofit.create(PokemonService::class.java)
    }

    fun createService(): PokemonService{
        return provideService(provideRetrofit())
    }

}