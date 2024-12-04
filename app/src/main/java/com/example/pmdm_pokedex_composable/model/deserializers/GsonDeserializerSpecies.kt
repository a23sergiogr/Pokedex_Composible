package com.example.pmdm_pokedex_composable.model.deserializers

import com.google.gson.*
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import java.lang.reflect.Type

class GsonDeserializerSpecies: JsonDeserializer<Species> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Species {
        val jsonObject = json.asJsonObject

        val flavorTexts = extractFlavorTexts(jsonObject)
        val varietiesList = extractVarieties(jsonObject, context)

        val baseHappiness = jsonObject.get("base_happiness")?.asInt ?: 0
        val captureRate = jsonObject.get("capture_rate")?.asInt ?: 0
        val color = jsonObject.getAsJsonObject("color")?.get("name")?.asString ?: "Desconocido"
        val evolutionChainURL = jsonObject.getAsJsonObjectOrNull("evolution_chain")?.get("url")?.asString
        val evolvesFromSpeciesName = jsonObject.getAsJsonObjectOrNull("evolves_from_species")?.get("name")?.asString
        val evolvesFromSpeciesURL = jsonObject.getAsJsonObjectOrNull("evolves_from_species")?.get("url")?.asString
        val formDescription = extractFormDescription(jsonObject)
        val formSwitchable = jsonObject.get("forms_switchable")?.asBoolean ?: false
        val genderRate = jsonObject.get("gender_rate")?.asInt ?: -1
        val genera = extractGenera(jsonObject)
        val growthRate = jsonObject.getAsJsonObjectOrNull("growth_rate")?.get("name")?.asString ?: "Desconocido"
        val habitat = jsonObject.getAsJsonObjectOrNull("habitat")?.get("name")?.asString
        val id = jsonObject.get("id")?.asInt ?: -1
        val name = jsonObject.get("name")?.asString ?: "Desconocido"

        return Species(
            baseHappiness = baseHappiness,
            captureRate = captureRate,
            color = color,
            evolutionChainURL = evolutionChainURL,
            evolvesFromSpeciesName = evolvesFromSpeciesName,
            evolvesFromSpeciesURL = evolvesFromSpeciesURL,
            flavorText = flavorTexts.ifEmpty { null },
            formDescription = formDescription,
            formSwitchable = formSwitchable,
            genderRate = genderRate,
            genera = genera,
            growthRate = growthRate,
            habitat = habitat,
            id = id,
            name = name,
            varieties = varietiesList.ifEmpty { null }
        )
    }

    private fun extractFlavorTexts(jsonObject: JsonObject): List<String> {
        val flavorArray = jsonObject.getAsJsonArray("flavor_text_entries") ?: JsonArray()
        val texts = mutableListOf<String>()
        for (element in flavorArray) {
            val entry = element.asJsonObject
            val language = entry.getAsJsonObject("language")?.get("name")?.asString
            if (language == "en") {
                texts.add(entry.get("flavor_text")?.asString ?: "")
            }
        }
        return texts
    }

    private fun extractVarieties(jsonObject: JsonObject, context: JsonDeserializationContext): List<NamedURLs> {
        val varietiesArray = jsonObject.getAsJsonArray("varieties") ?: JsonArray()
        val varietiesList = mutableListOf<NamedURLs>()
        for (element in varietiesArray) {
            element.asJsonObject.get("pokemon")?.let {
                varietiesList.add(context.deserialize(it, NamedURLs::class.java))
            }
        }
        return varietiesList
    }

    private fun extractFormDescription(jsonObject: JsonObject): List<String>? {
        val formDescriptions = jsonObject.getAsJsonArray("form_descriptions") ?: JsonArray()
        if (formDescriptions.size() > 0) {
            return listOf(formDescriptions.get(0).asJsonObject.get("description")?.asString ?: "")
        }
        return null
    }

    private fun extractGenera(jsonObject: JsonObject): String {
        val generaArray = jsonObject.getAsJsonArray("genera") ?: JsonArray()
        return if (generaArray.size() > 5) {
            generaArray.get(5).asJsonObject.get("genus")?.asString ?: "Desconocido"
        } else {
            "Desconocido"
        }
    }

    private fun JsonObject.getAsJsonObjectOrNull(key: String): JsonObject? {
        return this.get(key)?.takeIf { it.isJsonObject }?.asJsonObject
    }

}
/**
 * data class Species(
 *     val baseHappiness: Int,
 *     val captureRate: Int,
 *     val color: String,
 *     val evolutionChainURL: String?,
 *     val evolvesFromSpeciesName: String?,
 *     val evolvesFromSpeciesURL: String?,
 *     val flavorText: List<String>?,
 *     val formDescription: List<String>?,
 *     val formSwitchable: Boolean,
 *     val genderRate: Int,
 *     val genera: String,
 *     val growthRate: String,
 *     val habitad: String?,
 *     val id: Int,
 *     val name: String,
 *     val varieties: List<PokemonURL>
 * )
 */