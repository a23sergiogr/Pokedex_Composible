package com.example.pmdm_pokedex_composable.model.data_classes

data class Move(
    val id: Int,
    val name: String,
    val accuracy: Int,
    val pp: Int,
    val priority: Int,
    val power: Int,
    val damageClass: String,
    val type: String,
    val shortEffect: String,
    val flavorText: String
)
