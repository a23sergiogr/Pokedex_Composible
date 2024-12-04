package com.example.pmdm_pokedex_composable.model.deserializers

import com.example.pmdm_pokedex_composable.model.data_classes.TypeRelations
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonDeserializationContext
import java.lang.reflect.Type

class GsonDeserializerType : JsonDeserializer<TypeRelations.Type> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TypeRelations.Type {
        val jsonObject = json.asJsonObject

        val damageRelations = jsonObject["damage_relations"].asJsonObject
        val doubleDamageFrom = damageRelations["double_damage_from"].asJsonArray
        val halfDamageFrom = damageRelations["half_damage_from"].asJsonArray

        // Verificar si "no_damage_from" existe, si no, asignar una lista vacía
        val noDamageFrom = if (damageRelations.has("no_damage_from") && !damageRelations["no_damage_from"].isJsonNull) {
            damageRelations["no_damage_from"].asJsonArray
        } else {
            null // No es necesario agregar un valor null aquí, simplemente asignamos null
        }

        val doesNoDamage = mutableListOf<String>()
        val doesHalfDamage = mutableListOf<String>()
        val doesNormalDamage = mutableListOf<String>()
        val doesDoubleDamage = mutableListOf<String>()

        // Deserializar los nombres de los tipos desde los JSONs
        doubleDamageFrom.forEach { doesDoubleDamage.add(it.asJsonObject["name"].asString) }
        halfDamageFrom.forEach { doesHalfDamage.add(it.asJsonObject["name"].asString) }

        // Si noDamageFrom no es null, deserializamos normalmente
        noDamageFrom?.forEach { doesNoDamage.add(it.asJsonObject["name"].asString) }

        // Si noDamageFrom es null, simplemente dejamos la lista vacía

        // Crear el objeto 'Type' y agregarlo a la lista de tipos
        val type = TypeRelations.Type(
            name = jsonObject.get("name").asString,
            doesNoDamage = doesNoDamage, // Lista sin valores nulos
            doesHalfDamage = doesHalfDamage,
            doesNormalDamage = doesNormalDamage,
            doesDoubleDamage = doesDoubleDamage
        )

        return type
    }
}
