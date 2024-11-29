package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme


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
                color = Color(0xFFC62828),
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
                color = Color(0xFF1565C0),
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
                color = Color(0xFF2E7D32),
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


@Composable
fun CardGrassType(){
    ElevatedCard(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color(0xFF2E7D32),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = PokemonType.GRASS.color
        ),
    ) {
        Text( text = "Grass", color = PokemonType.GRASS.color)
    }
}


@Preview
@Composable
fun preview(){
    PMDM_Pokedex_ComposableTheme {
        CardGrassType()
    }
}