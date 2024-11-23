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
    primary = Purple80,        // Color principal
    secondary = DeepPurple,    // Acento morado profundo
    tertiary = Pink80,         // Acento rosado
    background = DarkGrey,     // Fondo oscuro
    surface = PurpleGrey80,    // Superficies ligeramente elevadas
    onPrimary = LightGrey,     // Texto sobre elementos principales
    onSecondary = WineRed,     // Elementos destacados en rojo vino
    onBackground = LightGrey   // Texto en fondos
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Color principal
    secondary = DeepPurple,    // Acento morado profundo
    tertiary = Pink40,         // Acento rosado
    background = LightGrey,    // Fondo claro
    surface = PurpleGrey40,    // Superficies ligeramente elevadas
    onPrimary = DarkGrey,      // Texto sobre elementos principales
    onSecondary = WineRed,     // Elementos destacados en rojo vino
    onBackground = DarkGrey    // Texto en fondos
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