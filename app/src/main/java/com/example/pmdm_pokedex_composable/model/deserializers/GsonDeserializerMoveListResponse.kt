package com.example.pmdm_pokedex_composable.model.deserializers

import com.example.pmdm_pokedex_composable.model.data_classes.MoveListResponse
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonDeserializerMoveListResponse : JsonDeserializer<MoveListResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): MoveListResponse {
        val jsonObject = json.asJsonObject
        val jsonArray = jsonObject.getAsJsonArray("results")
        val listURL = mutableListOf<NamedURLs>()
        jsonArray.forEach {
            listURL.add(context.deserialize(it,NamedURLs::class.java))
        }
        return MoveListResponse(listURL)
    }
}
