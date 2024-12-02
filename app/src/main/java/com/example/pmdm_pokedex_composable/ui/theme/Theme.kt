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

private val PurpleThemeDark = darkColorScheme(
    primary = Purple700,         // Color principal (morado oscuro)
    secondary = Magenta500,      // Acento magenta cálido
    tertiary = Lavender500,      // Acento lavanda
    background = DeepPurple,     // Fondo oscuro profundo
    surface = DarkPlum,          // Superficies elevadas
    onPrimary = White,           // Texto en blanco sobre morado
    onSecondary = Onyx,          // Texto en negro sobre magenta
    onBackground = White         // Texto en blanco sobre fondo oscuro
)

private val PurpleThemeLight = lightColorScheme(
    primary = Purple200,         // Color principal (morado claro)
    secondary = Magenta700,      // Acento magenta oscuro
    tertiary = Lavender600,      // Acento lavanda suave
    background = Ivory,          // Fondo claro cálido
    surface = SoftGray,          // Superficies claras
    onPrimary = Onyx,            // Texto negro sobre morado claro
    onSecondary = White,         // Texto en blanco sobre magenta oscuro
    onBackground = DeepPurple    // Texto en morado oscuro sobre fondo claro
)

private val YellowThemeDark = darkColorScheme(
    primary = Yellow700,         // Color principal (amarillo oscuro dorado)
    secondary = Orange500,       // Acento en naranja cálido
    tertiary = Gold500,          // Acento en dorado brillante
    background = DeepBrown,      // Fondo oscuro marrón profundo
    surface = RichAmber,         // Superficies elevadas
    onPrimary = Midnight,        // Texto en negro sobre amarillo
    onSecondary = White,         // Texto en blanco sobre naranja
    onBackground = White         // Texto en blanco sobre fondo oscuro
)

private val YellowThemeLight = lightColorScheme(
    primary = Yellow200,         // Color principal (amarillo pastel)
    secondary = Orange700,       // Acento en naranja oscuro
    tertiary = Gold600,          // Acento en dorado cálido
    background = Vanilla,        // Fondo claro cálido
    surface = LightTan,          // Superficies claras
    onPrimary = Midnight,        // Texto negro sobre amarillo pastel
    onSecondary = White,         // Texto en blanco sobre naranja oscuro
    onBackground = DeepBrown     // Texto en marrón profundo sobre fondo claro
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