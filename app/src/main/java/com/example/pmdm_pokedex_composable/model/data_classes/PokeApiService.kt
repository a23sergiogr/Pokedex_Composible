package com.example.pmdm_pokedex_composable.model.data_classes

import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerEvolutionChain
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerMove
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerMoveListResponse
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerNamedURL
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerPokedex
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerPokemon
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerSpecies
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerSprites
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerStats
import com.example.pmdm_pokedex_composable.model.deserializers.GsonDeserializerType
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokedex/{id}")
    suspend fun getPokedex(@Path("id") id: Int): Pokedex

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): Species

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") name: Int): Species

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") name: Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon

    @GET("pokemon-form/{id}/")
    suspend fun getPokemonForm(@Path("id") id: Int): Form

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: Int): Ability

    @GET("move/{name}")
    suspend fun getMove(@Path("name") name: String): Move

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") id: String): EvolutionChain

    @GET("move?limit=1200")
    suspend fun getMovesList(): MoveListResponse

    @GET("type/{id}")
    suspend fun getRelationType(@Path("id") id: String): TypeRelations.Type
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(Pokedex::class.java, GsonDeserializerPokedex())
                .registerTypeAdapter(Pokemon::class.java, GsonDeserializerPokemon())
                .registerTypeAdapter(Sprites::class.java, GsonDeserializerSprites())
                .registerTypeAdapter(Species::class.java, GsonDeserializerSpecies())
                .registerTypeAdapter(EvolutionChain::class.java, GsonDeserializerEvolutionChain())
                .registerTypeAdapter(Move::class.java, GsonDeserializerMove())
                .registerTypeAdapter(NamedURLs::class.java, GsonDeserializerNamedURL())
                .registerTypeAdapter(MoveListResponse::class.java, GsonDeserializerMoveListResponse())
                .registerTypeAdapter(Stat::class.java, GsonDeserializerStats())
                .registerTypeAdapter(TypeRelations.Type::class.java, GsonDeserializerType())
                .create()
        )
    ).build()

val pokeApiService = retrofit.create(PokeApiService::class.java)