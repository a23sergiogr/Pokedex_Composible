package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import com.example.pmdm_pokedex_composable.model.data_classes.TypeRelations
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs

@Composable
fun PokemonCombatView(
    types: List<String>
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding( horizontal = 20.dp)
    ){
        DisplayDamageEffectiveness(types)
    }
}

@Composable
fun DisplayDamageEffectiveness(typeNames: List<String>) {
    val typeRelations = remember { mutableStateOf(TypeRelations(emptyList())) }

    LaunchedEffect(typeNames) {
        typeRelations.value = PokemonDataController.getInstance().getTypeRelations()
    }

    val doubleDamage = mutableSetOf<String>()
    val halfDamage = mutableSetOf<String>()
    val noDamage = mutableSetOf<String>()
    val normalDamage = mutableSetOf<String>()

    // Agregamos las defensas de cada tipo
    typeNames.forEach { typeName ->
        val type = typeRelations.value.types.firstOrNull { it.name.equals(typeName, ignoreCase = true) }
        type?.let {
            doubleDamage.addAll(it.doesDoubleDamage)
            halfDamage.addAll(it.doesHalfDamage)
            noDamage.addAll(it.doesNoDamage)
            normalDamage.addAll(it.doesNormalDamage)
        }
    }

    // Si hay dos tipos, ajustamos defensas combinadas
    val firstType = typeRelations.value.types.firstOrNull { it.name.equals(typeNames[0], ignoreCase = true) }
    val secondType = typeRelations.value.types.firstOrNull { it.name.equals(typeNames[1], ignoreCase = true) }

    // Defensivas combinadas
    firstType?.let { ft ->
        secondType?.let { st ->
            doubleDamage.retainAll(ft.doesDoubleDamage.union(st.doesDoubleDamage))
            doubleDamage.removeAll(ft.doesHalfDamage)
            doubleDamage.removeAll(ft.doesNoDamage)
            doubleDamage.removeAll(st.doesHalfDamage)
            doubleDamage.removeAll(st.doesNoDamage)
            doubleDamage.removeAll(ft.doesDoubleDamage.intersect(st.doesDoubleDamage))

            halfDamage.retainAll(ft.doesHalfDamage.union(st.doesHalfDamage))
            halfDamage.removeAll(ft.doesDoubleDamage)
            halfDamage.removeAll(ft.doesNoDamage)
            halfDamage.removeAll(st.doesDoubleDamage)
            halfDamage.removeAll(st.doesNoDamage)
            halfDamage.removeAll(ft.doesHalfDamage.intersect(st.doesHalfDamage))
        }
    }

    Column {
        // Resistencia *4 (Recibe ¼ de daño)
        if (firstType?.doesDoubleDamage?.intersect(secondType?.doesDoubleDamage ?: emptySet()).isNullOrEmpty().not()) {
            Text("✴️ *4 de:")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                if (firstType != null) {
                    if (secondType != null) {
                        items(firstType.doesDoubleDamage.intersect(secondType.doesDoubleDamage).toList()) { type ->
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(75.dp)
                                    .padding(4.dp)
                            ) {
                                getTypeCardSafe(type).invoke()
                            }
                        }
                    }
                }
            }
        }

        // Resistencia *2 (Recibe ½ de daño)
        if (doubleDamage.isNotEmpty()) {
            Text("✨ *2 de:")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                items(doubleDamage.toList()) { type ->
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(75.dp)
                            .padding(4.dp)
                    ) {
                        getTypeCardSafe(type).invoke()
                    }
                }
            }
        }

        // Daño Normal (Resistencia Normal)
        if (normalDamage.isNotEmpty()) {
            Text("⚖️ Resistencia Normal")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                items(normalDamage.toList()) { type ->
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(75.dp)
                            .padding(4.dp)
                    ) {
                        getTypeCardSafe(type).invoke()
                    }
                }
            }
        }

        // Resistencia ½
        if (halfDamage.isNotEmpty()) {
            Text("⛨ ½ Resistencia")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                items(halfDamage.toList()) { type ->
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(75.dp)
                            .padding(4.dp)
                    ) {
                        getTypeCardSafe(type).invoke()
                    }
                }
            }
        }

        // Resistencia ¼
        if (firstType?.doesHalfDamage?.intersect(secondType?.doesHalfDamage ?: emptySet()).isNullOrEmpty().not()) {
            Text("⛨ ¼ Resistencia")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                if (firstType != null) {
                    if (secondType != null) {
                        items(firstType.doesHalfDamage.intersect(secondType.doesHalfDamage).toList()) { type ->
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(75.dp)
                                    .padding(4.dp)
                            ) {
                                getTypeCardSafe(type).invoke()
                            }
                        }
                    }
                }
            }
        }

        // Sin Daño (No resiste daño)
        if (noDamage.isNotEmpty()) {
            Text("⛔ Invulnerable a")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),  // Limita a 4 elementos por fila
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
            ) {
                items(noDamage.toList()) { type ->
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(75.dp)
                            .padding(4.dp)
                    ) {
                        getTypeCardSafe(type).invoke()
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonMoveList(
    movesList: List<NamedURLs>,
    ){
    val rememberedMoves = remember { movesList }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(24.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RectangleShape
            )
    ) {
        if (rememberedMoves.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                items(rememberedMoves, key = { it.url }) { namedURL ->
                    MoveCard(
                        namedURLs = namedURL,
                        onClick = {}
                    )
                }
            }
        } else {
            Text(
                text = "No moves available",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
