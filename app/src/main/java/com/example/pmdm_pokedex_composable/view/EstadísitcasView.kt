package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme

@Composable
fun Estadisticas(
    ps: String,
    attack: String,
    defence: String,
    spaAttack: String,
    spaDefence: String,
    speed: String,
    total: String
) {
    // Convertimos las estadísticas a valores numéricos
    val stats = listOf(
        ps.toFloatOrNull() ?: 0f,
        attack.toFloatOrNull() ?: 0f,
        defence.toFloatOrNull() ?: 0f,
        spaAttack.toFloatOrNull() ?: 0f,
        spaDefence.toFloatOrNull() ?: 0f,
        speed.toFloatOrNull() ?: 0f
    )

    // Encontrar la estadística más alta
    val maxStat = stats.maxOrNull() ?: 1f // Para evitar dividir por 0, usamos un valor por defecto de 1f

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estadísticas",
                fontWeight = FontWeight.Bold
            )

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 12.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                EstadisticasCard("Base", {})
//                EstadisticasCard("Min", {})
//                EstadisticasCard("Max", {})
//            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // Nombres de las estadísticas
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    listOf(
                        "PS", "Attack", "Defence", "Spa. Attack", "Spa. Defence", "Speed"
                    ).forEach { text ->
                        Text(
                            text = text,
                            modifier = Modifier
                                .padding(vertical = 4.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }

                    // Mostrar el Total como solo número
                    Text(
                        text = "Total",
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }

                // Barras de progreso con tamaños proporcionales, sin barra para Total
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    stats.forEach { stat ->
                        ProgressBar(stat / maxStat, stat.toString())
                    }

                    // Mostrar el Total solo como número
                    Text(
                        text = total,
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressBar(progress: Float, number: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .padding(start = 26.dp)
            .height(20.dp)
            .width(150.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(16.dp)) // Fondo de la barra
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .width((150 * progress).dp)
                .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(16.dp)) // Progreso
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 6.dp),
                textAlign = TextAlign.Right,
                text = number,
            )
        }
    }
}


@Preview
@Composable
fun PreviewEstadisticas(){
    PMDM_Pokedex_ComposableTheme {
        Estadisticas(
            "45",
            "49",
            "49",
            "65",
            "65",
            "40",
            "305"
        )
    }
}