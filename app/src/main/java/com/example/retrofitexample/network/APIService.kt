package com.example.retrofitexample.network

import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
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
    client(OkHttpClient()).build()
    return client.create(APIService::class.java)
}