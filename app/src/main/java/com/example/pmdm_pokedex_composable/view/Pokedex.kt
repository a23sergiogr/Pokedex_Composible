package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.model.data_classes.Pokedex
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pmdm_pokedex_composable.controler.NavControllerManager
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import java.util.Locale


@Composable
fun Pokedex(
    drawerState: DrawerState,
) {
    val pokemonDataController = PokemonDataController.getInstance()
    setUIColors(darkenColor(MaterialTheme.colorScheme.primary, 0.2f))

    val pokemonList = remember { mutableStateListOf<PokemonCardData>() }
    val loading = remember { mutableStateOf(true) }
    var pokedex: Pokedex

    LaunchedEffect(Unit) {
        try {
            loading.value = false

            pokedex = pokemonDataController.getPokedex()

            pokedex.pokemonEntries.forEach {
                pokemonList.add(
                    PokemonCardData(
                        name = it.pokemonSpecies.name,
                        id = it.entryNumber.toString(),
                    )
                )
            }

            loading.value = true

        } catch (e: Exception) {
            loading.value = false
        }
    }


    Scaffold(
        topBar = {
            TopBar(
                drawerState = drawerState,
                title = "Pokedex",
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SearchBarPokemon(
                list = pokemonList, // lista de objetos Pokemon
                card = { pokemon ->
                    PokedexCard(
                        pokemonCardData = pokemon,
                    )
                }
            )
        }
    }
}

@Composable
fun PokedexCard(
    pokemonCardData: PokemonCardData,
) {
    val pokemonDataController = PokemonDataController.getInstance()

    val navController = NavControllerManager.getNavController()

    // Estado de carga y pokemon
    val loading = remember { mutableStateOf(true) }
    val pokemon = remember { mutableStateOf<Pokemon?>(null) }

    // Llamada asíncrona
    LaunchedEffect(pokemonCardData.name) {
        try {
            loading.value = true

            // Simula la llamada para obtener los datos del Pokémon
            pokemon.value = pokemonDataController.getPokemon(pokemonCardData.name)

            loading.value = false
        } catch (e: Exception) {
            loading.value = false
        }
    }

    // Colores del tema
    val textColor = MaterialTheme.colorScheme.onPrimary
    val primaryColor = MaterialTheme.colorScheme.primary

    // Cargo datos de los pokemons
    val types =  pokemon.value?.types
    val typeOne = if (!types.isNullOrEmpty() && types.isNotEmpty()) types[0].name else null
    val typeTwo = if (!types.isNullOrEmpty() && types.size > 1) types[1].name else null

    val typeOneCard = getTypeCardSafe(typeOne)
    val typeTwoCard = getTypeCardSafe(typeTwo)

    val image = pokemon.value?.sprites?.normalSprites?.get(0) ?: ""


    val painter =  rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current).data(image).apply(block = fun ImageRequest.Builder.() {
            crossfade(true)
            placeholder(R.drawable.placeholder_ditto)
            error(R.drawable.error_unown)
        }).build())

    ElevatedCard(
        onClick = {
            navController?.navigate("PokemonView/${pokemonCardData.id}")
        },
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
                        text = pokemonCardData.name.replaceFirstChar { it.uppercase(Locale.getDefault()) },
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor // Usar color primario del tema
                    )
                    Text(
                        text = pokemonCardData.id,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor // Usar color secundario del tema para el ID
                    )
//                    Image(
//                        painter = painterResource(R.drawable.stars_shiny),
//                        contentDescription = "stars",
//                        modifier = Modifier.width(24.dp)
//                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(75.dp)
                            .height(30.dp)
                            .padding(end = 4.dp)
                            .align(Alignment.CenterVertically)
                    ){
                        typeOneCard.invoke()
                    }
                    Box(
                        modifier = Modifier
                            .width(75.dp)
                            .height(30.dp)
                            .padding(end = 4.dp)
                            .align(Alignment.CenterVertically)
                    ){
                        typeTwoCard.invoke()
                    }
                }
            }

            // Mostrar la imagen del pokemon cargado
            Image(
                painter = painter,
                contentDescription = pokemonCardData.name,
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .fillMaxHeight()
            )
        }
    }
}


data class PokemonCardData(
    val name: String,
    val id: String,
)