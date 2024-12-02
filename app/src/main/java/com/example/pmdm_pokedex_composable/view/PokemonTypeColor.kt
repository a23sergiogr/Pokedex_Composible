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

    companion object {
        fun fromName(name: String): PokemonType? {
            return PokemonType.values().find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

enum class PokemonColor(val hexCode: Long) {
    BLACK(0xFF2D2D2D),  // Negro ligeramente más claro
    BLUE(0xFF7BA1D1),   // Azul más claro
    BROWN(0xFF8E7F6D),  // Marrón más claro
    GRAY(0xFFB3B3B3),   // Gris más claro
    GREEN(0xFF86C300),  // Verde más claro
    PINK(0xFFF1B7A7),   // Rosa más claro
    PURPLE(0xFF9E7DBA), // Púrpura más claro
    RED(0xFFEC8A74),    // Rojo más claro
    WHITE(0xFFDBDBDB),  // Blanco más claro
    YELLOW(0xFFFFD15C), // Amarillo más claro
    NULL(0xFFF);        // Valor por defecto

    companion object {
        fun fromName(name: String): PokemonColor? {
            return values().find { it.name.equals(name, ignoreCase = true) }
        }
    }
}


fun lightenColor(color: Color, factor: Float): Color {
    // Extraemos los componentes del color
    val red = color.red + (1f - color.red) * factor
    val green = color.green + (1f - color.green) * factor
    val blue = color.blue + (1f - color.blue) * factor
    val alpha = color.alpha // Mantener la transparencia igual

    return Color(red, green, blue, alpha)
}


fun darkenColor(color: Color, factor: Float): Color {
    // Extraemos los componentes del color
    val red = color.red * (1f - factor)
    val green = color.green * (1f - factor)
    val blue = color.blue * (1f - factor)
    val alpha = color.alpha // Mantener la transparencia igual

    return Color(red, green, blue, alpha)
}

fun tranparentColor(color: Color): Color {
    // Extraemos los componentes del color
    val red = color.red
    val green = color.green
    val blue = color.blue
    val alpha = 0f

    return Color(red, green, blue, alpha)
}


fun tranparentColor(color: Color, factor: Float): Color {
    // Extraemos los componentes del color
    val red = color.red
    val green = color.green
    val blue = color.blue
    val alpha = color.alpha * (1f - factor)

    return Color(red, green, blue, alpha)
}
