package com.example.pmdm_pokedex_composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme

@Composable
fun Moves(
    typeColor: Color,
    moveColor: Long,
    imgType: Painter,
    name: String,
    typo: String,
    power: String,
    accuracy: String,
    pp: String,
    priority: String,
    flavorText: String,
    effect: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(typeColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
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
                        .fillMaxWidth()
                )
            }

            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = imgType,
                    contentDescription = "type01",
                    modifier = Modifier
                        .size(140.dp)
                )

                when(typo){
                    "physical"-> PhysicalCard()
                    "special"-> SpecialCard()
                    "status"-> StatusCard()
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
                    )

                    Text(
                        text = power,
                    )


                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "PP",
                    )

                    Text(
                        text = pp,
                    )
                }
                Column {
                    Text(
                        text = "Accuracy",
                    )

                    Text(
                        text = accuracy,
                    )


                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Priority",
                    )

                    Text(
                        text = priority,
                    )
                }
            }

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor/2)
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
                    color = Color(moveColor*2)
                )

                Text(
                    modifier = Modifier.padding(26.dp),
                    text = flavorText,
                )
            }

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor/2)
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
                    color = Color(moveColor*2)
                )

                Text(
                    modifier = Modifier.padding(26.dp),
                    text = effect,
                )
            }
        }
    }
}

@Composable
fun PhysicalCard() {
    ElevatedCard(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color(0xFFC62828),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFFCDD2)
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Physical",
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(R.drawable.physical_move),
                contentDescription = "physical_move",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun SpecialCard() {
    ElevatedCard(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color(0xFF1565C0),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFBBDEFB)
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Special",
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(R.drawable.special_move),
                contentDescription = "special_move",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun StatusCard() {
    ElevatedCard(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color(0xFF2E7D32),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFC8E6C9)
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status",
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(R.drawable.status_move),
                contentDescription = "status_move",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}



@Preview
@Composable
fun PreviewMoves(){
    PMDM_Pokedex_ComposableTheme {
        Moves(
            MaterialTheme.colorScheme.background,
            0xFF95c799,
            painterResource(R.drawable.type_grass),
            "Gigadrenado",
            "special",
            "75",
            "100",
            "15",
            "0",
            "Steals 1/2 of the damage inflicted",
            "Inflicts regular damage. Drains half the damage inflicted to heal the user"
        )
    }
}