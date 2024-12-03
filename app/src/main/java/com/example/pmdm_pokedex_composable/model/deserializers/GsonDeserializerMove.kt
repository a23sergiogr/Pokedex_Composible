package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Move
import com.google.gson.JsonArray
import java.lang.reflect.Type

class GsonDeserializerMove : JsonDeserializer<Move> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Move {
        val jsonObject = json.asJsonObject

        return Move(
            id = jsonObject.get("id")?.asInt ?: -1, // Valor predeterminado para ID
            name = jsonObject.get("name")?.asString ?: "Unknown Move", // Nombre predeterminado
            pp = jsonObject.get("pp")?.asInt ?: 0, // PP predeterminado
            power = jsonObject.get("power")?.asInt ?: 0, // Poder predeterminado
            priority = jsonObject.get("priority")?.asInt ?: 0, // Prioridad predeterminada
            accuracy = jsonObject.get("accuracy")?.asInt ?: 100, // Precisión predeterminada
            damageClass = jsonObject.getAsJsonObject("damage_class")?.get("name")?.asString ?: "unknown", // Clase de daño predeterminada
            type = jsonObject.getAsJsonObject("type")?.get("name")?.asString ?: "normal", // Tipo predeterminado
            shortEffect = jsonObject.getAsJsonArray("effect_entries")?.getOrNull(0)
                ?.asJsonObject?.get("short_effect")?.asString ?: "No effect available", // Efecto predeterminado
            flavorText = jsonObject.getAsJsonArray("flavor_text_entries")?.getOrNull(0)
                ?.asJsonObject?.get("flavor_text")?.asString ?: "No flavor text available" // Texto predeterminado
        )
    }

    // Extensión para manejar índices de arrays de manera segura
    private fun JsonArray.getOrNull(index: Int): JsonElement? =
        if (index in 0 until this.size()) this[index] else null
}
