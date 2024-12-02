package com.example.pmdm_pokedex_composable.model.data_classes

import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs


data class Ability(
    val id: Int,
    val name: String,
    val effect: String?,
    val pokemon: List<PokemonURL>?
) {
    class PokemonURL (
        val isHidden: Boolean,
        val url: NamedURLs
    )
}

