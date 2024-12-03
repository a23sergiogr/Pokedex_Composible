package com.example.pmdm_pokedex_composable.model.data_classes

data class TypeRelations(
    val types: List<Type>
) {
    data class Type (
        val name: String,
        val doesNoDamage: List<String>,      // Tipos cuyos ataques no hacen daño a este tipo
        val doesHalfDamage: List<String>,   // Tipos cuyos ataques hacen la mitad de daño
        val doesNormalDamage: List<String>, // Tipos cuyos ataques hacen daño normal
        val doesDoubleDamage: List<String>  // Tipos cuyos ataques hacen el doble de daño
    )
}