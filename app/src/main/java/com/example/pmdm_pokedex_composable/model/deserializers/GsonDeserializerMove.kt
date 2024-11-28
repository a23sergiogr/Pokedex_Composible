package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Move
import java.lang.reflect.Type

class GsonDeserializerMove: JsonDeserializer<Move> {
    override fun deserialize(
    json: JsonElement,
    typeOfT: Type,
    context: JsonDeserializationContext
    ): Move {
        val jsonObject = json.asJsonObject
        return Move(
            id = jsonObject.get("id").asInt,
            name = jsonObject.get("name").asString,
            pp = jsonObject.get("pp").asInt,
            power = jsonObject.get("power").asInt,
            priority = jsonObject.get("priority").asInt,
            accuracy = jsonObject.get("accuracy").asInt,
            damageClass = jsonObject.getAsJsonObject("damage_class").get("name").asString,
            type = jsonObject.getAsJsonObject("type").get("name").asString,
            shortEffect = jsonObject.getAsJsonArray("effect_entries").get(0).asJsonObject.get("short_effect").asString,
            flavorText = jsonObject.getAsJsonArray("flavor_text_entries").get(0).asJsonObject.get("flavor_text").asString,
        )
    }
}