package com.example.retrofitexample.data.repository

import android.util.Log
import com.example.retrofitexample.data.model.PokemonListModel
import com.example.retrofitexample.network.APIService
import com.example.retrofitexample.network.getRetrofitClient
import retrofit2.Response

interface PokemonListRepositoryInterface {
    suspend fun getPokemonList(offset: Int, limit: Int):
            Response<PokemonListModel>
}
class PokemonListRepository(
    private val apiService: APIService= getRetrofitClient())
    : PokemonListRepositoryInterface{
    override suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): Response<PokemonListModel> {
        Log.d("Repository getPokemonlist","$offset,$limit")
        return apiService.getPokemonList(offset=offset,limit=limit)
    }
}