package com.example.pmdm_pokedex_composable.model.data_classes

import data_classes.urlclasses.NamedURLs

data class Species(
    val baseHappiness: Int,
    val captureRate: Int,
    val color: String,
    val evolutionChainURL: String?,
    val evolvesFromSpeciesName: String?,
    val evolvesFromSpeciesURL: String?,
    val flavorText: List<String>?,
    val formDescription: List<String>?,
    val formSwitchable: Boolean?,
    val genderRate: Int?,
    val genera: String?,
    val growthRate: String?,
    val habitat: String?,
    val id: Int,
    val name: String,
    val varieties: List<NamedURLs>?
)