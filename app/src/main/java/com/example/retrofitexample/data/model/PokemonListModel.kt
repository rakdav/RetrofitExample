package com.example.retrofitexample.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListModel(
    @SerializedName("count") val count:Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonListItem>
): Parcelable

@Parcelize
data class PokemonListItem(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
): Parcelable
