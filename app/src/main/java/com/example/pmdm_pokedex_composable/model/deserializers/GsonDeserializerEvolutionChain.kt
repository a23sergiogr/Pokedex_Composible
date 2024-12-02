package com.example.pmdm_pokedex_composable.model.deserializers

import com.example.pmdm_pokedex_composable.model.data_classes.EvolutionChain
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import com.google.gson.*
import java.lang.reflect.Type

class GsonDeserializerEvolutionChain : JsonDeserializer<EvolutionChain> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): EvolutionChain {
        val evolutionChain = mutableListOf<NamedURLs>()

        // Acceder a la cadena evolutiva
        val chain = json.asJsonObject.getAsJsonObject("chain")

        // Recorremos la cadena de evoluciones recursivamente
        fun extractEvolutions(chainElement: JsonObject) {
            val species = chainElement.getAsJsonObject("species")
            val name = species.get("name").asString
            val url = species.get("url").asString
            evolutionChain.add(NamedURLs(name, url))

            // Si hay más evoluciones, las recorremos
            val evolvesTo = chainElement.getAsJsonArray("evolves_to")
            for (evolution in evolvesTo) {
                extractEvolutions(evolution.asJsonObject)
            }
        }

        // Iniciamos el proceso de extracción desde el primer elemento
        extractEvolutions(chain)

        return EvolutionChain(evolutionChain)
    }
}