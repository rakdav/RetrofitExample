package com.example.retrofitexample.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.retrofitexample.R
import com.example.retrofitexample.viewmodel.PokemonListViewModel

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null)
        Text(
            text = "You got an error!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel= viewModel()
) {
    val pokemonList = viewModel.pokemonList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect (pokemonList) {
        viewModel.getPokemonList()
    }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Button (
                onClick = { viewModel.getPokemonList() }
            ) {
                Text(text = "Reload")
            }
            if(errorMessage.value!=null) ErrorState()
            else if(isLoading.value){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(200.dp),
                        color = Color.Blue,
                        trackColor = Color.Red
                    )
                }
            }
            else
            {
                LazyColumn {
                    pokemonList.value?.let { result->
                        items(result.results.size){
                            index->
                            val name=result.results[index].name
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonCell(
    modifier: Modifier = Modifier,
    index: String,
    name: String
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .height(64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = index, fontSize = 20.sp)
            Text(text = name, fontSize = 20.sp, modifier = Modifier.padding(start = 16.dp))
        }
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 20.dp))
    }
}
