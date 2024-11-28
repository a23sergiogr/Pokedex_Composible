package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.example.pmdm_pokedex_composable.model.data_classes.Form
import data_classes.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerForm: JsonDeserializer<Form> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Form {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("name")?.asString
        val order = jsonObject.get("order").asInt
        val formOrder = jsonObject.get("form_order")?.asInt ?: 0
        val isDefault = jsonObject.get("is_default")?.asBoolean ?: false
        val isBattleOnly = jsonObject.get("is_battle_only")?.asBoolean ?: false
        val isMega = jsonObject.get("is_mega")?.asBoolean ?: false
        val formName = jsonObject.get("form_name")?.asString ?: ""

        val pokemon = context.deserialize<NamedURLs>(jsonObject.getAsJsonObject("pokemon"), NamedURLs::class.java)

        val typesArray = jsonObject.getAsJsonArray("types")
        val types = typesArray.map { context.deserialize<NamedURLs>(jsonObject.getAsJsonObject("type"), NamedURLs::class.java) }

        val versionGroup = context.deserialize<NamedURLs>(jsonObject.getAsJsonObject("version_group"), NamedURLs::class.java)

        return Form(
            id = id,
            name = name,
            order = order,
            formOrder = formOrder,
            isDefault = isDefault,
            isBattleOnly = isBattleOnly,
            isMega = isMega,
            formName = formName,
            pokemon = pokemon,
            types = types,
            versionGroup = versionGroup
        )



    }

}