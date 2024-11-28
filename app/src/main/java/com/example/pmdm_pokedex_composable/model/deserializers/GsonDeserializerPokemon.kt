package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.example.pmdm_pokedex_composable.model.data_classes.Stat
import data_classes.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerPokemon: JsonDeserializer<Pokemon> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Pokemon {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("name").asString
        val baseExperience = jsonObject.get("base_experience")?.asInt
        val height = jsonObject.get("height")?.asInt
        val weight = jsonObject.get("weight")?.asInt

        val abilitiesArray = jsonObject.get("abilities")?.asJsonArray
        val abilities = abilitiesArray?.map { context.deserialize<NamedURLs>(it.asJsonObject.getAsJsonObject("ability"), NamedURLs::class.java) }

        val cries = jsonObject.getAsJsonObject("cries")?.get("latest")?.asString

        val isDefault = jsonObject.get("is_default")?.asBoolean

        val movesArray = jsonObject.get("moves")?.asJsonArray
        val moves = movesArray?.map { context.deserialize<NamedURLs>(it.asJsonObject.getAsJsonObject("move"), NamedURLs::class.java) }

        val species = jsonObject.get("species")?.asJsonObject?.let { context.deserialize<NamedURLs>(it, NamedURLs::class.java) }

        val statsArray = jsonObject.get("stats")?.asJsonArray
        val stats = statsArray?.map { context.deserialize<Stat>(it, Stat::class.java) }

        val sprites = context.deserialize<Sprites>(jsonObject.getAsJsonObject("sprites"), Sprites::class.java)

        val typesArray = jsonObject.get("types")?.asJsonArray
        val types = typesArray?.map { context.deserialize<NamedURLs>(it.asJsonObject.getAsJsonObject("type"), NamedURLs::class.java) }

        val forms = jsonObject.getAsJsonArray("forms").map { context.deserialize<NamedURLs>(it.asJsonObject, NamedURLs::class.java) }

        return Pokemon(
            id = id,
            name = name,
            baseExperience = baseExperience,
            height = height,
            isDefault = isDefault,
            weight = weight,
            abilities = abilities,
            forms = forms,
            moves = moves,
            species = species,
            sprites = sprites,
            cries = cries,
            stats = stats,
            types = types
        )

    }
}
/**
 *
 *     val baseExperience: Int?,
 *     val height: Int?,
 *     val isDefault: Boolean?,
 *     val weight: Int?,
 *     val abilities: List<NamedURLs>?,
 *     val forms: List<NamedURLs>?,
 *     val moves: List<NamedURLs>?,
 *     val species: NamedURLs?,
 *     val sprites: Sprites?,
 *     val cries: String?,
 *     val stats: List<Stat>?,
 *     val types: List<NamedURLs>?,
 */