package com.example.retrofitexample.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.retrofitexample.data.model.PokemonDetailsModel
import com.example.retrofitexample.data.model.PokemonListModel
import com.example.retrofitexample.data.repository.PokemonDetailRepository
import com.example.retrofitexample.data.repository.PokemonListRepository
import com.example.retrofitexample.data.repository.PokemonListRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonDetailViewModel(private val repository: PokemonDetailRepository=
    PokemonDetailRepository()
    ): ViewModel()
    {
        private val _pokemonDetail= MutableStateFlow< PokemonDetailsModel?>(null)
        private val _errorMessage= MutableStateFlow<String?>(null)
        private val _isLoading= MutableStateFlow<Boolean>(true)

        val pokemonListDetails: StateFlow<PokemonDetailsModel?> get() = _pokemonDetail.asStateFlow()
        val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
        val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
        suspend fun getPokemonDetails(name: String){
            _isLoading.value=true
            val response=repository.getPokemonDetail(name)
            if(response.isSuccessful){
                val body=response.body()
                if(body!=null){
                    Log.d("Success","$body?.size")
                    _isLoading.value=false
                    _pokemonDetail.value=body
                }
                else
                {
                    val error=response.errorBody()
                    if(error!=null){
                        Log.d("Pokemon list error",error.string())
                        _isLoading.value=false
                        _errorMessage.value=error.toString()
                    }
                }
            }
        }
    }

