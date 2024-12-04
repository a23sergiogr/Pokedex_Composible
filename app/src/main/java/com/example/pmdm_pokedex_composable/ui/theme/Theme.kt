package com.example.pmdm_pokedex_composable.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

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

val RocketThemeLight = lightColorScheme(
    primary = RocketPinkLight,               // Rosa vibrante
    secondary = RocketPurpleLight,           // Morado oscuro
    tertiary = RocketGreyLight,              // Gris oscuro
    background = RocketWhiteLight,           // Fondo blanco
    surface = RocketLightGreyLight,          // Superficies suaves
    onPrimary = RocketBlackLight,            // Texto negro sobre rosa
    onSecondary = RocketWhiteLight,          // Texto blanco sobre morado oscuro
    onBackground = RocketDarkPurpleLight     // Texto morado oscuro sobre fondo blanco
)

val MagmaThemeLight = lightColorScheme(
    primary = MagmaRedLight,                 // Rojo oscuro
    secondary = MagmaOrangeLight,            // Naranja cálido
    tertiary = MagmaBrownLight,              // Marrón oscuro
    background = MagmaBeigeLight,            // Fondo cálido
    surface = MagmaLightBrownLight,          // Superficies en tonos tierra
    onPrimary = MagmaWhiteLight,             // Texto blanco sobre rojo oscuro
    onSecondary = MagmaBlackLight,           // Texto negro sobre naranja cálido
    onBackground = MagmaDarkBrownLight       // Texto marrón oscuro sobre fondo beige
)

val AquaThemeLight = lightColorScheme(
    primary = AquaBlueLight,                 // Azul profundo
    secondary = AquaTealLight,               // Verde azulado
    tertiary = AquaCyanLight,                // Cian claro
    background = AquaLightBlueLight,         // Fondo claro
    surface = AquaLightCyanLight,            // Superficies suaves
    onPrimary = AquaWhiteLight,              // Texto blanco sobre azul
    onSecondary = AquaWhiteLight,            // Texto blanco sobre verde azulado
    onBackground = AquaDarkBlueLight         // Texto azul oscuro sobre fondo claro
)

val GalacticThemeLight = lightColorScheme(
    primary = GalacticDarkBlueLight,         // Azul oscuro
    secondary = GalacticSilverLight,        // Plata metálica
    tertiary = GalacticPurpleLight,         // Púrpura cósmico
    background = GalacticBlackLight,        // Fondo negro
    surface = GalacticDarkGreyLight,        // Superficies oscuras
    onPrimary = GalacticWhiteLight,         // Texto blanco sobre azul
    onSecondary = GalacticBlackTextLight,   // Texto negro sobre plata
    onBackground = GalacticLightGreyLight   // Texto gris claro sobre fondo negro
)

val PlasmaThemeLight = lightColorScheme(
    primary = PlasmaGreenLight,              // Verde oscuro
    secondary = PlasmaBlackLight,            // Negro
    tertiary = PlasmaLimeLight,              // Verde lima oscuro
    background = PlasmaWhiteLight,           // Fondo claro
    surface = PlasmaLightGreyLight,          // Superficies suaves
    onPrimary = PlasmaWhiteLight,            // Texto blanco sobre verde
    onSecondary = PlasmaWhiteLight,          // Texto blanco sobre negro
    onBackground = PlasmaDarkGreenLight      // Texto verde claro sobre fondo blanco
)

val FlareThemeLight = lightColorScheme(
    primary = FlareRedLight,                 // Rojo fuego
    secondary = FlareYellowLight,            // Amarillo
    tertiary = FlareGoldLight,               // Dorado
    background = FlareDarkGreyLight,         // Fondo oscuro
    surface = FlareLightGreyLight,           // Superficies claras
    onPrimary = FlareWhiteLight,             // Texto blanco sobre rojo
    onSecondary = FlareBlackLight,           // Texto negro sobre amarillo
    onBackground = FlareDarkRedLight         // Texto rojo oscuro sobre fondo oscuro
)

val RocketThemeDark = darkColorScheme(
    primary = RocketPinkDark,             // Rosa vibrante (para mantener la identidad)
    secondary = RocketPurpleDark,         // Morado oscuro (sombra de villanía)
    tertiary = RocketGreyDark,            // Gris muy oscuro (para resaltar el contraste)
    background = RocketBackgroundDark,       // Fondo morado oscuro
    surface = RocketSurfaceDark,             // Superficies oscuras
    onPrimary = RocketOnPrimaryDark,             // Texto negro sobre rosa (contraste muy fuerte)
    onSecondary = RocketOnSecondaryDark,           // Texto blanco sobre morado oscuro
    onBackground = RocketOnBackgroundDark           // Texto blanco sobre fondo morado
)

val MagmaThemeDark = darkColorScheme(
    primary = MagmaRedDark,              // Rojo intenso (magma)
    secondary = MagmaOrangeDark,         // Naranja cálido (fuego)
    tertiary = MagmaBrownDark,           // Marrón oscuro (con un contraste más fuerte)
    background = MagmaBackgroundDark,          // Fondo rojo oscuro (intenso)
    surface = MagmaSurfaceDark,           // Superficies marrón oscuro
    onPrimary = MagmaOnPrimaryDark,             // Texto blanco sobre rojo intenso
    onSecondary = MagmaOnSecondaryDark,           // Texto negro sobre naranja
    onBackground = MagmaOnBackgroundDark      // Texto marrón claro sobre fondo oscuro
)

val AquaThemeDark = darkColorScheme(
    primary = AquaBlueDark,             // Azul oscuro (mar y océano profundo)
    secondary = AquaTealDark,           // Verde azulado oscuro
    tertiary = AquaCyanDark,            // Cian profundo
    background = AquaBackgroundDark,         // Fondo azul marino
    surface = AquaSurfaceDark,             // Superficies azules oscuras
    onPrimary = AquaOnPrimaryDark,             // Texto blanco sobre azul oscuro
    onSecondary = AquaOnSecondaryDark,           // Texto blanco sobre verde azulado oscuro
    onBackground = AquaOnBackgroundDark       // Texto azul claro sobre fondo oscuro
)

val GalacticThemeDark = darkColorScheme(
    primary = GalacticDarkBlueDark,           // Azul oscuro (el espacio profundo)
    secondary = GalacticSilverDark,          // Plata metálica oscura
    tertiary = GalacticPurpleDark,           // Púrpura muy oscuro (cósmico)
    background = GalacticBackgroundDark,    // Fondo negro (como el espacio)
    surface = GalacticSurfaceDark,           // Superficies oscuras metálicas
    onPrimary = GalacticOnPrimaryDark,       // Texto blanco sobre azul oscuro
    onSecondary = GalacticOnSecondaryDark,   // Texto negro sobre plata
    onBackground = GalacticOnBackgroundDark // Texto gris claro sobre fondo negro
)

val PlasmaThemeDark = darkColorScheme(
    primary = PlasmaGreenDark,               // Verde muy oscuro (naturaleza y poder)
    secondary = PlasmaBlackDark,             // Negro para acentuar la rebeldía
    tertiary = PlasmaLimeDark,               // Verde lima oscuro (contraste brillante)
    background = PlasmaBackgroundDark,      // Fondo verde oscuro (suave)
    surface = PlasmaSurfaceDark,             // Superficies grises oscuras
    onPrimary = PlasmaOnPrimaryDark,         // Texto blanco sobre verde muy oscuro
    onSecondary = PlasmaOnSecondaryDark,     // Texto blanco sobre negro
    onBackground = PlasmaOnBackgroundDark   // Texto verde claro sobre fondo oscuro
)

val FlareThemeDark = darkColorScheme(
    primary = FlareRedDark,              // Rojo muy oscuro (fuego intenso)
    secondary = FlareYellowDark,         // Amarillo oscuro (fuego quemado)
    tertiary = FlareGoldDark,            // Dorado oscuro
    background = FlareBackgroundDark,   // Fondo gris oscuro (serio y peligroso)
    surface = FlareSurfaceDark,         // Superficies grises
    onPrimary = FlareOnPrimaryDark,      // Texto blanco sobre rojo oscuro
    onSecondary = FlareOnSecondaryDark,  // Texto negro sobre amarillo oscuro
    onBackground = FlareOnBackgroundDark // Texto rojo sobre fondo gris oscuro
)


enum class ThemeType {
    Purple,         // Tema morado
    Yellow,         // Tema amarillo
    Default,        // Tema por defecto
    Rocket,         // Tema de Equipo Rocket
    Magma,          // Tema de Equipo Magma
    Aqua,           // Tema de Equipo Aqua
    Galactic,       // Tema de Equipo Galáctico
    Plasma,         // Tema de Equipo Plasma
    Flare           // Tema de Equipo Flare
}

class SettingsViewModel : ViewModel() {
    var selectedTheme by mutableStateOf(ThemeType.Default)
        private set

    fun setTheme(theme: ThemeType) {
        selectedTheme = theme
    }
}

// Función Composable para aplicar el tema
@Composable
fun PMDM_Pokedex_ComposableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    themeType: ThemeType = ThemeType.Default,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> when (themeType) {
            ThemeType.Purple -> {
                if (isSystemInDarkTheme()) PurpleThemeDark else PurpleThemeLight
            }
            ThemeType.Yellow -> {
                if (isSystemInDarkTheme()) YellowThemeDark else YellowThemeLight
            }
            ThemeType.Rocket -> {
                if (isSystemInDarkTheme()) RocketThemeDark else RocketThemeLight
            }
            ThemeType.Magma -> {
                if (isSystemInDarkTheme()) MagmaThemeDark else MagmaThemeLight
            }
            ThemeType.Aqua -> {
                if (isSystemInDarkTheme()) AquaThemeDark else AquaThemeLight
            }
            ThemeType.Galactic -> {
                if (isSystemInDarkTheme()) GalacticThemeDark else GalacticThemeLight
            }
            ThemeType.Plasma -> {
                if (isSystemInDarkTheme()) PlasmaThemeDark else PlasmaThemeLight
            }
            ThemeType.Flare -> {
                if (isSystemInDarkTheme()) FlareThemeDark else FlareThemeLight
            }
            else -> if (isSystemInDarkTheme())  DarkColorScheme else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}