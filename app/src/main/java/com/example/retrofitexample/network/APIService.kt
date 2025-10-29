package com.example.retrofitexample.network

import com.example.retrofitexample.data.model.PokemonDetailsModel
import com.example.retrofitexample.data.model.PokemonListModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset:Int,
        @Query("limit") limit:Int,
    ): Response<PokemonListModel>
    @GET("pokemon/{name}")
    suspend fun getPokemonData(
        @Path("name") name: String
    ):Response<PokemonDetailsModel>
}
fun getRetrofitClient(): APIService{
    val client= Retrofit.Builder().
            baseUrl("https://pokeapi.co/api/v2/").
        addConverterFactory(GsonConverterFactory.create()).
    client(OkHttpClient()).build()
    return client.create(APIService::class.java)
}