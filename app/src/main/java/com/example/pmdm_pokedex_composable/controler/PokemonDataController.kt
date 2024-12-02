package com.example.pmdm_pokedex_composable.controler

import com.example.pmdm_pokedex_composable.model.data_classes.EvolutionChain
import com.example.pmdm_pokedex_composable.model.data_classes.PokeApiService
import com.example.pmdm_pokedex_composable.model.data_classes.Pokedex
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDataController private constructor(private val pokeApiService: PokeApiService) {

    companion object {
        // Variable para almacenar la instancia única
        @Volatile
        private var INSTANCE: PokemonDataController? = null

        // Método para obtener la instancia única
        fun getInstance(pokeApiService: PokeApiService): PokemonDataController {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PokemonDataController(pokeApiService).also { INSTANCE = it }
            }
        }
    }

    // Funciónes suspendidas que obtienen datos de la API
    suspend fun getPokedex(): Pokedex {
        return pokeApiService.getPokedex(1)
    }

    suspend fun getPokemon(name: String): Pokemon {
        return pokeApiService.getPokemon(name)
    }

    suspend fun getPokemonByUrl(url: String): Pokemon {
        return pokeApiService.getPokemon(extractIdFromUrl(url))
    }

    suspend fun getSpecies(name: String): Species {
        return pokeApiService.getPokemonSpecies(name)
    }

    suspend fun getEvolutionChain(url: String): EvolutionChain{
        return pokeApiService.getEvolutionChain(extractIdFromUrl(url))
    }

    private fun extractIdFromUrl(url: String): String {
        val regex = """evolution-chain/(\d+)""".toRegex()
        val matchResult = regex.find(url)
        val result = matchResult?.groupValues?.get(1) ?: ""
        return  result // Si no encuentra el ID, devuelve -1
    }


}
