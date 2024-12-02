package com.example.pmdm_pokedex_composable.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import java.util.Locale


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
private fun TypeCard(baseColor: PokemonType, type: String) {
    ElevatedCard(
        modifier = Modifier
            .padding(end = 4.dp)
            .border(
                width = 2.dp,
                color = darkenColor(Color(baseColor.color.value), 0.2f),
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = lightenColor(Color(baseColor.color.value), 0.6f)
        ),
    ) {
        Box(
            modifier = Modifier
                .width(75.dp)
                .padding(vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = type,
                color = darkenColor(Color(baseColor.color.value), 0.2f),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun LoadingTypeCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .size(80.dp, 40.dp)
            .padding(4.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Gray)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp
            )
        }
    }
}


private enum class TypeCards(val typeCard: @Composable () -> Unit) {
    NORMAL({ TypeCard(PokemonType.NORMAL, "Normal") }),
    FIRE({ TypeCard(PokemonType.FIRE, "Fire") }),
    WATER({ TypeCard(PokemonType.WATER, "Water") }),
    ELECTRIC({ TypeCard(PokemonType.ELECTRIC, "Electric") }),
    GRASS({ TypeCard(PokemonType.GRASS, "Grass") }),
    ICE({ TypeCard(PokemonType.ICE, "Ice") }),
    FIGHTING({ TypeCard(PokemonType.FIGHTING, "Fighting") }),
    POISON({ TypeCard(PokemonType.POISON, "Poison") }),
    GROUND({ TypeCard(PokemonType.GROUND, "Ground") }),
    FLYING({ TypeCard(PokemonType.FLYING, "Flying") }),
    PSYCHIC({ TypeCard(PokemonType.PSYCHIC, "Psychic") }),
    BUG({ TypeCard(PokemonType.BUG, "Bug") }),
    ROCK({ TypeCard(PokemonType.ROCK, "Rock") }),
    GHOST({ TypeCard(PokemonType.GHOST, "Ghost") }),
    DRAGON({ TypeCard(PokemonType.DRAGON, "Dragon") }),
    DARK({ TypeCard(PokemonType.DARK, "Dark") }),
    STEEL({ TypeCard(PokemonType.STEEL, "Steel") }),
    FAIRY({ TypeCard(PokemonType.FAIRY, "Fairy") }),
    LOADING({  }); // Simbolizar estado de carga
}

fun getTypeCardSafe(typeName: String?): @Composable (() -> Unit) {
    return try {
        if (typeName != null) {
            TypeCards.valueOf(typeName.uppercase(Locale.ROOT)).typeCard
        } else TypeCards.LOADING.typeCard
    } catch (e: IllegalArgumentException) {
        TypeCards.LOADING.typeCard
    }
}