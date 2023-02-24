package com.example.pokedir.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.pokedir.data.remote.responses.Pokemon
import com.example.pokedir.repository.PokemonRepository
import com.example.pokedir.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}