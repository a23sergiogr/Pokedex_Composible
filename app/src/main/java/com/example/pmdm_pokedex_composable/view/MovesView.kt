package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign



@Composable
fun Moves(
    typeColor: Color,
    moveColor: Long,
    name: String,
    damageCard: @Composable () -> Unit,
    typeCard: @Composable () -> Unit,
    power: String,
    accuracy: String,
    pp: String,
    priority: String,
    flavorText: String,
    effect: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(lightenColor(typeColor,0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = darkenColor(typeColor, 0.6f)
                )
            }

            Column (
                modifier = Modifier
                    .padding(top = 12.dp)
                    .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            lightenColor(typeColor,0.2f),
                            darkenColor(MaterialTheme.colorScheme.primary, 0.2f)
                        )
                    )
                )
            ){
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)

                ){
                    Row(
                        modifier = Modifier
                            .width(130.dp)
                            .height(30.dp)
                            .align(Alignment.CenterVertically)
                            .background(
                                color = darkenColor(typeColor, 0.6f),
                                shape = RoundedCornerShape(12.dp) // Bordes redondeados para un mejor aspecto
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .width(45.dp)
                                .padding(start = 4.dp), // Pequeño espacio interno
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Type: ",
                                color = Color.White // Texto claro para mayor contraste
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(75.dp),
                        ) {
                            typeCard.invoke() // Invoca la tarjeta del tipo correspondiente
                        }
                    }

                    Box(
                        modifier = Modifier
                            .width(75.dp)
                            .height(30.dp)
                            .align(Alignment.CenterVertically)
                    ){
                        damageCard.invoke()
                    }
                }
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column {
                        Text(
                            text = "Power",
                            color = darkenColor(typeColor, 0.6f)
                        )

                        Text(
                            text = power,
                            color = darkenColor(typeColor, 0.6f)
                        )


                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = "PP",
                            color = darkenColor(typeColor, 0.6f)
                        )

                        Text(
                            text = pp,
                            color = darkenColor(typeColor, 0.6f)
                        )
                    }
                    Column {
                        Text(
                            text = "Accuracy",
                            color = darkenColor(typeColor, 0.6f)
                        )

                        Text(
                            text = accuracy,
                            color = darkenColor(typeColor, 0.6f)
                        )


                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = "Priority",
                            color = darkenColor(typeColor, 0.6f)
                        )

                        Text(
                            text = priority,
                            color = darkenColor(typeColor, 0.6f)
                        )
                    }
                }

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = tranparentColor(lightenColor(typeColor, 0.6f), 0.6f)
                    ),
                    modifier = Modifier.padding( horizontal = 16.dp, vertical = 26.dp),
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth(),
                        text = "Description",
                        color = darkenColor(typeColor, 0.6f)
                    )

                    Text(
                        modifier = Modifier.padding(26.dp),
                        text = flavorText,
                        color = darkenColor(typeColor, 0.6f)
                    )
                }

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = tranparentColor(lightenColor(typeColor, 0.6f), 0.6f)
                    ),
                    modifier = Modifier.padding( horizontal = 16.dp, vertical = 26.dp),
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .fillMaxWidth(),
                        text = "Effect",
                        color = darkenColor(typeColor, 0.6f)
                    )

                    Text(
                        modifier = Modifier.padding(26.dp),
                        text = effect,
                        color = darkenColor(typeColor, 0.6f)
                    )
                }
            }
        }
    }
}