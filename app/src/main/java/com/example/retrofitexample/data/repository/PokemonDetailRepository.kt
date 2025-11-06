package com.example.retrofitexample.data.repository

import android.util.Log
import com.example.retrofitexample.data.model.PokemonDetailsModel
import com.example.retrofitexample.data.model.PokemonListModel
import com.example.retrofitexample.network.APIService
import com.example.retrofitexample.network.getRetrofitClient
import retrofit2.Response

interface PokemonDetailRepositoryInterface {
    suspend fun getPokemonDetail(name: String):
    Response<PokemonDetailsModel>
}
class PokemonDetailRepository(
    private val apiService: APIService= getRetrofitClient()
): PokemonDetailRepositoryInterface{
    override suspend fun getPokemonDetail(name: String): Response<PokemonDetailsModel> {
        Log.d("Repository getPokemon detail", name)
        return apiService.getPokemonData(name=name)
    }
}

