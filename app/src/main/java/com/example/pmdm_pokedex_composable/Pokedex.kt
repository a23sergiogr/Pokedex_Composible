package com.example.pmdm_pokedex_composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Pokedex(){

}

/**
 * Renderiza una lista de tarjetas (`PokedexCard`) en una columna perezosa (`LazyColumn`).
 */
@Composable
fun CardList(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "Pokedex"
    ) {
        composable("PokemonView") { PokemonView() }
    }

    LazyColumn (
        Modifier.background(MaterialTheme.colorScheme.background)
    ){
        items(100) { i ->
            PokedexCard(
                name = "Bulbasaur",
                id = "#$i",
                pokemonImage = painterResource(R.drawable.bulbasaur),
                imgType01 = painterResource(R.drawable.type_grass),
                imgType02 = painterResource(R.drawable.type_poison),
                onClick = { navController.navigate("PokemonView")}
            )
        }
    }
}


/**
 * Representa una tarjeta de información de un Pokémon.
 *
 * Esta tarjeta contiene:
 * - Nombre del Pokémon.
 * - ID del Pokémon.
 * - Imagen del Pokémon.
 * - Hasta dos imágenes de tipos (opcional el segundo tipo).
 */
@Composable
fun PokedexCard(
    name: String,
    id: String,
    pokemonImage: Painter,
    imgType01: Painter,
    imgType02: Painter?,
    onClick: @Composable () -> Unit
) {
    // Obtén los colores del tema actual
    val textColor = MaterialTheme.colorScheme.onPrimary
    val primaryColor = MaterialTheme.colorScheme.primary

    ElevatedCard(
        onClick = {onClick},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = primaryColor // Aplicar el color de fondo del tema
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor // Usar color primario del tema
                    )
                    Text(
                        text = id,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor // Usar color secundario del tema para el ID
                    )
                    Image(
                        painter = painterResource(R.drawable.stars_shiny),
                        contentDescription = "stars",
                        modifier = Modifier.width(24.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = imgType01,
                        contentDescription = "type01",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 10.dp, 0.dp)
                            .size(80.dp)
                    )
                    if (imgType02 != null) {
                        Image(
                            painter = imgType02,
                            contentDescription = "type02",
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                                .size(80.dp)
                        )
                    }
                }
            }

            Image(
                painter = pokemonImage,
                contentDescription = name,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            )
        }
    }
}
