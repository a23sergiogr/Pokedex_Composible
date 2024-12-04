package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

private enum class PokemonTypeCards(val typeCard: @Composable () -> Unit) {
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
            PokemonTypeCards.valueOf(typeName.uppercase(Locale.ROOT)).typeCard
        } else PokemonTypeCards.LOADING.typeCard
    } catch (e: IllegalArgumentException) {
        PokemonTypeCards.LOADING.typeCard
    }
}

enum class DamageType(val damageCard: @Composable () -> Unit) {
    PHYSICAL({TypeCard(PokemonType.PHYSICAL, "Physical")}),
    SPECIAL({TypeCard(PokemonType.SPECIAL, "Special")}),
    STATUS({TypeCard(PokemonType.STATUS, "Status")}),
    LOADING({ });
}

fun getDamageCardSafe(damageTypeName: String?): @Composable (() -> Unit) {
    return try {
        if (damageTypeName != null) {
            DamageType.valueOf(damageTypeName.uppercase(Locale.ROOT)).damageCard
        } else DamageType.LOADING.damageCard
    } catch (e: IllegalArgumentException) {
        DamageType.LOADING.damageCard
    }
}

@Composable
private fun TypeCard(baseColor: PokemonType, type: String) {
    ElevatedCard(
        modifier = Modifier
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
                .fillMaxSize()
                .padding(vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = type.uppercase(Locale.ROOT),
                color = darkenColor(Color(baseColor.color.value), 0.2f),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}
