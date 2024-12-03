package com.example.pmdm_pokedex_composable.view
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import com.example.pmdm_pokedex_composable.ui.theme.SettingsViewModel
import com.example.pmdm_pokedex_composable.ui.theme.ThemeType


@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = viewModel()) {
    setUIColors(darkenColor(MaterialTheme.colorScheme.primary, 0.2f))
    // Obtener el tema seleccionado desde el ViewModel
    val selectedTheme = settingsViewModel.selectedTheme

    // Función para cambiar el tema cuando se selecciona una opción
    val onThemeChange: (ThemeType) -> Unit = { theme ->
        settingsViewModel.setTheme(theme)
    }

    // Aplicamos el tema según el seleccionado
    PMDM_Pokedex_ComposableTheme(
        themeType = selectedTheme,
        dynamicColor = false,
    ) {
        // UI de selección de tema
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selecciona un tema", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Botones para cambiar el tema
            ThemeOption("Tema Claro", ThemeType.Default, selectedTheme, onThemeChange)
            ThemeOption("Tema Morado", ThemeType.Purple, selectedTheme, onThemeChange)
            ThemeOption("Tema Amarillo", ThemeType.Yellow, selectedTheme, onThemeChange)
            ThemeOption("Tema Rocket", ThemeType.Rocket, selectedTheme, onThemeChange)
            ThemeOption("Tema Magma", ThemeType.Magma, selectedTheme, onThemeChange)
            ThemeOption("Tema Aqua", ThemeType.Aqua, selectedTheme, onThemeChange)
            ThemeOption("Tema Galáctico", ThemeType.Galactic, selectedTheme, onThemeChange)
            ThemeOption("Tema Plasma", ThemeType.Plasma, selectedTheme, onThemeChange)
            ThemeOption("Tema Flare", ThemeType.Flare, selectedTheme, onThemeChange)
        }
    }
}

@Composable
fun ThemeOption(
    title: String,
    themeType: ThemeType,
    selectedTheme: ThemeType,
    onThemeChange: (ThemeType) -> Unit
) {
    val isSelected = selectedTheme == themeType

    Button(
        onClick = { onThemeChange(themeType) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = title,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}
