package com.example.retrofitexample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailsModel(
    val abilities: List<Abilities>,
    @SerializedName("sprites") val sprites: Sprites,
):Parcelable
@Parcelize
data class Abilities(
    @SerializedName("ability") val ability: Ability,
    @SerializedName("is_hidden") val is_hidden: Boolean,
    @SerializedName("slot") val slot: Int
): Parcelable

@Parcelize
data class Ability(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
): Parcelable

@Parcelize
data class Sprites(
    @SerializedName("back_default") val back_default: String
): Parcelable