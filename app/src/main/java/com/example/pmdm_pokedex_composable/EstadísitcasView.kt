package com.example.pmdm_pokedex_composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme

@Composable
fun Estadisticas(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                EstadisticasCard("Base", {})
                EstadisticasCard("Min", {})
                EstadisticasCard("Max", {})
            }
            Row {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PS",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Attack",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "PS",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Attack",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


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
fun Estadistica(estadisticaString: String, estadistica: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp, bottom = 0.dp, start = 26.dp, end = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = estadisticaString,
            fontWeight = FontWeight(1000)
        )

        Text(
            text = estadistica,
            fontWeight = FontWeight(1000)
        )

        ProgressBar(0.5f)
    }
}

@Composable
fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .padding(start = 26.dp)
            .height(26.dp)
            .width(150.dp)
            .background(Color(0xFF3949AB), shape = RoundedCornerShape(16.dp)) // Fondo de la barra
    ) {
        Box(
            modifier = Modifier
                .height(26.dp)
                .width((150*progress).dp)
                .background(Color(0xFFF9A825), shape = RoundedCornerShape(16.dp)) // Progreso
        )
    }
}

@Preview
@Composable
fun PreviewEstadisticas(){
    PMDM_Pokedex_ComposableTheme {
        Estadisticas()
    }
}