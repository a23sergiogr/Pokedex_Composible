package com.example.pmdm_pokedex_composable.view

import androidx.compose.ui.graphics.Color

enum class PokemonType(val color: Color) {
    NORMAL(Color(0xFFA8A878)),    // Color para el tipo NORMAL
    FIRE(Color(0xFFF08030)),      // Color para el tipo FUEGO
    WATER(Color(0xFF6890F0)),     // Color para el tipo AGUA
    ELECTRIC(Color(0xFFF8D030)),  // Color para el tipo ELÉCTRICO
    GRASS(Color(0xFF78C850)),     // Color para el tipo PLANTA
    ICE(Color(0xFF98D8D8)),       // Color para el tipo HIELO
    FIGHTING(Color(0xFFC03028)),  // Color para el tipo LUCHA
    POISON(Color(0xFFA040A0)),    // Color para el tipo VENENO
    GROUND(Color(0xFFE0C068)),    // Color para el tipo TIERRA
    FLYING(Color(0xFF98A8F0)),    // Color para el tipo VOLADOR
    PSYCHIC(Color(0xFFF85888)),   // Color para el tipo PSÍQUICO
    BUG(Color(0xFFA8B820)),       // Color para el tipo BICHOS
    ROCK(Color(0xFFB8A038)),      // Color para el tipo ROCA
    GHOST(Color(0xFF705898)),     // Color para el tipo FANTASMA
    DRAGON(Color(0xFF7038F8)),    // Color para el tipo DRAGÓN
    DARK(Color(0xFF705848)),      // Color para el tipo SINIESTRO
    STEEL(Color(0xFFB8B8D0)),     // Color para el tipo ACERO
    FAIRY(Color(0xFFF0A0F0));     // Color para el tipo HADA
}

fun lightenColor(color: Long, factor: Float = 0.2f): Long {
    // Extraemos los componentes de color (Rojo, Verde, Azul)
    val alpha = (color shr 24) and 0xFF
    val red = (color shr 16) and 0xFF
    val green = (color shr 8) and 0xFF
    val blue = color and 0xFF

    // Aplicamos un factor para aclarar cada componente
    val newRed = (red + (255 - red) * factor).toInt()
    val newGreen = (green + (255 - green) * factor).toInt()
    val newBlue = (blue + (255 - blue) * factor).toInt()

    // Recompone el color claro con los nuevos valores
    return (alpha shl 24) or ((newRed shl 16).toLong()) or ((newGreen shl 8).toLong()) or newBlue.toLong()
}