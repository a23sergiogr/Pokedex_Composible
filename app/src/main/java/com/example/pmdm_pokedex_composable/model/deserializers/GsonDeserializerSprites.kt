package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import java.lang.reflect.Type

class GsonDeserializerSprites: JsonDeserializer<Sprites> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Sprites {
        val jsonObject = json.asJsonObject

        val normalSprites = mutableListOf<String>()
        jsonObject.get("front_default")?.asString?.let { normalSprites.add(it) }
        jsonObject.get("back_default")?.asString?.let { normalSprites.add(it) }

        val shinySprites = mutableListOf<String>()
        jsonObject.get("front_shiny")?.asString?.let { shinySprites.add(it) }
        jsonObject.get("back_shiny")?.asString?.let { shinySprites.add(it) }

        val sprites = Sprites(
            normalSprites = if (normalSprites.isNotEmpty()) normalSprites else null,
            shinySprites = if (shinySprites.isNotEmpty()) shinySprites else null
        )

        return sprites

    }
}