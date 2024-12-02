package com.example.pmdm_pokedex_composable.model.data_classes

import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs

data class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int?,
    val height: Int?,
    val isDefault: Boolean?,
    val weight: Int?,
    val abilities: List<NamedURLs>?,
    val forms: List<NamedURLs>?,
    val moves: List<NamedURLs>?,
    val species: NamedURLs?,
    val sprites: Sprites?,
    val cries: String?,
    val stats: List<Stat>?,
    val types: List<NamedURLs>?,
)
