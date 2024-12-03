package com.example.pmdm_pokedex_composable.model.deserializers

import com.example.pmdm_pokedex_composable.model.data_classes.TypeRelations
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonDeserializerType : JsonDeserializer<TypeRelations.Type> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TypeRelations.Type {
        val jsonObject = json.asJsonObject

        val doubleDamageFrom = jsonObject["damage_relations"].asJsonObject["double_damage_from"].asJsonArray
        val halfDamageFrom = jsonObject["damage_relations"].asJsonObject["half_damage_from"].asJsonArray
        val noDamageFrom = jsonObject["damage_relations"].asJsonObject["no_damage_from"].asJsonArray

        val doesNoDamage = mutableListOf<String>()
        val doesHalfDamage = mutableListOf<String>()
        val doesNormalDamage = mutableListOf<String>()
        val doesDoubleDamage = mutableListOf<String>()

        // Deserializar los nombres de los tipos desde los JSONs
        doubleDamageFrom.forEach { doesDoubleDamage.add(it.asJsonObject["name"].asString) }
        halfDamageFrom.forEach { doesHalfDamage.add(it.asJsonObject["name"].asString) }
        noDamageFrom.forEach { doesHalfDamage.add(it.asJsonObject["name"].asString) }


        // Crear el objeto 'Type' y agregarlo a la lista de tipos
        val type = TypeRelations.Type(
            name = jsonObject.get("name").asString,
            doesNoDamage = doesNoDamage,
            doesHalfDamage = doesHalfDamage,
            doesNormalDamage = doesNormalDamage,
            doesDoubleDamage = doesDoubleDamage
        )

        return type
    }
}