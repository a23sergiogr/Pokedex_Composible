package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
<<<<<<< HEAD:app/src/main/java/com/example/pmdm_pokedex_composable/view/Pokedex.kt
import coil.compose.rememberImagePainter
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.model.data_classes.PokeApiService
import com.example.pmdm_pokedex_composable.model.data_classes.Pokedex
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.example.pmdm_pokedex_composable.model.data_classes.pokeApiService

=======
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
>>>>>>> ae1e30742ccb2c7e31f4262103dac852f27b663b:app/src/main/java/com/example/pmdm_pokedex_composable/Pokedex.kt


@Composable
fun Pokedex(
    drawerState: DrawerState,
    navController: NavHostController
) {
    // Estado para almacenar la lista de Pokémon
    val pokemonEntries = remember { mutableStateOf<List<Pokedex.PokemonEntries>>(emptyList()) }
    val loading = remember { mutableStateOf(true) }

    // LaunchedEffect para hacer la solicitud a la API
    LaunchedEffect(Unit) {
        try {
            // Aquí puedes cambiar el ID de Pokedex si es necesario
            val pokedex = pokeApiService.getPokedex(1)
            pokemonEntries.value = pokedex.pokemonEntries
            loading.value = false
        } catch (e: Exception) {
            println("Error al obtener datos: ${e.message}")
            loading.value = false
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                drawerState = drawerState,
                title = "Pokedex",
                actions = {
                    IconButton(onClick = { /* Acción de filtrar */ }) {
                        Icons.Default.Add
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            // Verifica si los datos están cargando
            if (loading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                // Pasa los pokemonEntries deserializados a CardListPokedex
                CardListPokedex(navController, pokemonEntries.value, pokeApiService)
            }
        }
    }
}


/**
 * Renderiza una lista de tarjetas (`PokedexCard`) en una columna perezosa (`LazyColumn`).
 */
@Composable
<<<<<<< HEAD:app/src/main/java/com/example/pmdm_pokedex_composable/view/Pokedex.kt
fun CardListPokedex(
    navController: NavHostController,
    pokemonEntries: List<Pokedex.PokemonEntries>,
    pokeApiService: PokeApiService
) {
    LazyColumn(Modifier.background(MaterialTheme.colorScheme.background)) {
        items(pokemonEntries) { entry ->
            // Estado para el Pokémon actual
            val loading = remember { mutableStateOf(true) }
            val pokemon = remember { mutableStateOf<Pokemon?>(null) }

            // Realiza la solicitud solo si el Pokémon aún no se ha cargado
            LaunchedEffect(entry.pokemonSpecies.name) {
                try {
                    val pokemonData = pokeApiService.getPokemon(entry.pokemonSpecies.name)
                    pokemon.value = pokemonData
                    loading.value = false
                } catch (e: Exception) {
                    println("Error al obtener datos: ${e.message}")
                    loading.value = false
                }
            }

            if (loading.value) {
                CircularProgressIndicator()
            } else {
                val sprites = pokemon.value?.sprites
                val pokemonImage = sprites?.normalSprites?.get(0)

                PokedexCard(
                    name = entry.pokemonSpecies.name,
                    id = pokemon.value?.id.toString(),
                    pokemonImage = (pokemonImage ?: painterResource(R.drawable.bulbasaur)).toString(), // Imagen por defecto en caso de error
                    imgType01 = painterResource(R.drawable.type_grass),
                    imgType02 = painterResource(R.drawable.type_poison),
                    onClick = { navController.navigate("PokemonView") }
                )
            }
=======
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
>>>>>>> ae1e30742ccb2c7e31f4262103dac852f27b663b:app/src/main/java/com/example/pmdm_pokedex_composable/Pokedex.kt
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
    pokemonImage: String,
    imgType01: Painter,
    imgType02: Painter?,
    onClick: @Composable () -> Unit
) {
    // Obtén los colores del tema actual
    val textColor = MaterialTheme.colorScheme.onPrimary
    val primaryColor = MaterialTheme.colorScheme.primary

    val painter = rememberImagePainter(
        pokemonImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.placeholder_ditto)
            error(R.drawable.error_unown_question_mark)
        }
    )


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
            containerColor = primaryColor
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
                painter = painter,
                contentDescription = name,
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .fillMaxHeight()
            )
        }
    }
}