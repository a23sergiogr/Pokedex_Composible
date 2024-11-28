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
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
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
                fontWeight = FontWeight(1000)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EstadisticasCard("Base", {})
                EstadisticasCard("Min", {})
                EstadisticasCard("Max", {})
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ){
                    Text(
                        text = "PS",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Attack",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Defence",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Spa. Attack",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Spa. Defence",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Speed",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Total",
                        modifier = Modifier
                            .padding( vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.padding(12.dp)
                ){
                    ProgressBar(0.50f, ps)
                    ProgressBar(0.35f, attack)
                    ProgressBar(0.60f, defence)
                    ProgressBar(0.55f, spaAttack)
                    ProgressBar(0.80f, spaDefence)
                    ProgressBar(0.45f, speed)
                    ProgressBar(0.55f, total)
                }
            }
        }
    }
}

@Composable
fun EstadisticasCard(text: String, onClick: () -> Unit){
    ElevatedCard(
        modifier = Modifier
            .width(80.dp)
            .border(
                width = 2.dp,
                color = Color(0xFFF9A825),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFFF59D)
        ),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProgressBar(progress: Float, number: String) {
    Box(
        modifier = Modifier
            .padding( vertical = 6.dp)
            .padding(start = 26.dp)
            .height(20.dp)
            .width(150.dp)
            .background(Color(0xFF3949AB), shape = RoundedCornerShape(16.dp)) // Fondo de la barra
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .width((150*progress).dp)
                .background(Color(0xFFF9A825), shape = RoundedCornerShape(16.dp)) // Progreso
        ){
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