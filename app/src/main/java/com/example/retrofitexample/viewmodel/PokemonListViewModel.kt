package com.example.retrofitexample.viewmodel

import androidx.compose.runtime.Recomposer
import androidx.lifecycle.ViewModel
import com.example.retrofitexample.data.model.PokemonListModel
import com.example.retrofitexample.data.repository.PokemonListRepository
import com.example.retrofitexample.data.repository.PokemonListRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonListViewModel(
    private val repository: PokemonListRepositoryInterface= PokemonListRepository()
): ViewModel() {
    private val _pokemonList= MutableStateFlow<PokemonListModel?>(null)
    private val _errorMessage= MutableStateFlow<String?>(null)
    private val _isLoading= MutableStateFlow<Boolean>(true)

    val pokemonList: StateFlow<PokemonListModel?> get() = _pokemonList.asStateFlow()
    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
    fun getPokemonList(){

    }

}