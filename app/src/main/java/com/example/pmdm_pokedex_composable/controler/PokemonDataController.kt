package com.example.pmdm_pokedex_composable.controler

import com.example.pmdm_pokedex_composable.model.data_classes.EvolutionChain
import com.example.pmdm_pokedex_composable.model.data_classes.Move
import com.example.pmdm_pokedex_composable.model.data_classes.PokeApiService
import com.example.pmdm_pokedex_composable.model.data_classes.Pokedex
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.example.pmdm_pokedex_composable.model.data_classes.TypeRelations
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

//Cambiar a Object
class PokemonDataController private constructor(private val pokeApiService: PokeApiService) {

    companion object {
        // Variable para almacenar la instancia única
        @Volatile
        private var INSTANCE: PokemonDataController? = null

        // Variable para almacenar el servicio API
        @Volatile
        private var storedPokeApiService: PokeApiService? = null

        // Método para obtener la instancia única
        fun getInstance(pokeApiService: PokeApiService? = null): PokemonDataController {
            return INSTANCE ?: synchronized(this) {
                // Si no hay instancia, verifica si ya se almacenó el servicio API
                val service = storedPokeApiService ?: pokeApiService
                ?: throw IllegalArgumentException("PokeApiService must be provided the first time")
                // Guarda el servicio si es la primera vez
                if (storedPokeApiService == null) {
                    storedPokeApiService = service
                }
                // Crea la instancia con el servicio disponible
                INSTANCE ?: PokemonDataController(service).also { INSTANCE = it }
            }
        }
    }

    val BASE_URL = "https://pokeapi.co/api/v2/"
    var cachedTypeRelations: TypeRelations? = null

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

    suspend fun getMovesList(): List<NamedURLs>{
        return pokeApiService.getMovesList().results
    }

    suspend fun getMove(name: String): Move{
        return if (name.contains(BASE_URL + "move/")){
            val str = name.substringAfter(BASE_URL + "move/")
            pokeApiService.getMove(str)
        }
        else
            pokeApiService.getMove(name)
    }

    suspend fun getTypeRelations(): TypeRelations {
        // Verificar si ya tenemos los datos almacenados
        cachedTypeRelations?.let {
            return it  // Si ya están en cache, los usamos directamente
        }

        val list = withContext(Dispatchers.IO) {
            (1..19).map { i ->
                async { pokeApiService.getRelationType(i.toString()) }
            }.awaitAll()  // Esperar todas las tareas de manera concurrente
        }

        cachedTypeRelations = TypeRelations(list)
        return cachedTypeRelations!!
    }


    private fun extractIdFromUrl(url: String): String {
        val regex = """evolution-chain/(\d+)""".toRegex()
        val matchResult = regex.find(url)
        val result = matchResult?.groupValues?.get(1) ?: ""
        return  result // Si no encuentra el ID, devuelve -1
    }
}
