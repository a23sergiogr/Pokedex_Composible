package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Ability
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerAbility: JsonDeserializer<Ability> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Ability {
        val jsonObject = json.asJsonObject

        val pokemons = mutableListOf<Ability.PokemonURL>()
        val pokemonsElement = jsonObject.getAsJsonArray("pokemon")
        for (element in pokemonsElement){
            val obj = element.asJsonObject
            pokemons.add(
                Ability.PokemonURL(
                isHidden = obj .get("is_hidden").asBoolean,
                url = context.deserialize(obj.get("pokemon"), NamedURLs::class.java)
            ))
        }

        return Ability(
            id = jsonObject.get("id").asInt,
            name = jsonObject.get("name").asString,
            effect = jsonObject.getAsJsonArray("effect_entries").get(1).asJsonObject.get("effect").asString,
            pokemon = pokemons,
        )
    }
}