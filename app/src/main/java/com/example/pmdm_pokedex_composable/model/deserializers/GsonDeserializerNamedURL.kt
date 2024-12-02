package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerNamedURL: JsonDeserializer<NamedURLs> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): NamedURLs {
        val jsonObject = json.asJsonObject
        return NamedURLs(
            jsonObject.get("name").asString,
            jsonObject.get("url").asString
        )
    }}