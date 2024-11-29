package com.example.pmdm_pokedex_composable.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Teal200,         // Color principal (un verde-azul suave y brillante)
    secondary = Amber500,      // Acento cálido (ámbar para un contraste llamativo)
    tertiary = Lime500,        // Acento en verde lima para detalles vivos
    background = Charcoal,     // Fondo oscuro (más suave y moderno que un gris común)
    surface = DarkSlate,       // Superficies elevadas, con un toque de azul oscuro
    onPrimary = Black,         // Texto en negro para visibilidad
    onSecondary = DarkRed,     // Texto o elementos en un rojo oscuro
    onBackground = White       // Texto en blanco sobre el fondo oscuro
)


private val LightColorScheme = lightColorScheme(
    primary = Teal700,         // Color principal (un tono más oscuro de verde-azul)
    secondary = Amber600,      // Acento cálido (amarillo anaranjado medio)
    tertiary = Lime600,        // Acento en verde más saturado
    background = OffWhite,     // Fondo claro, pero no tan blanco para suavizar
    surface = LightSlate,      // Superficies ligeramente elevadas con un toque de gris azulado
    onPrimary = White,         // Texto en blanco sobre elementos principales
    onSecondary = DarkSlate,   // Texto en gris oscuro para resaltar los acentos
    onBackground = Charcoal    // Texto en gris oscuro sobre el fondo claro
)



@Composable
fun PMDM_Pokedex_ComposableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}