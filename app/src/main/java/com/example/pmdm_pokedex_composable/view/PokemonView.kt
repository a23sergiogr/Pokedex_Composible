package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.example.pmdm_pokedex_composable.model.data_classes.pokeApiService
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun PokemonView(
    pokemonId: String?
){

    // Estado de carga y pokemon
    val loading = remember { mutableStateOf(true) }
    val pokemon = remember { mutableStateOf<Pokemon?>(null) }
    val specie = remember { mutableStateOf<Species?>(null) }
    val pokemonDataController = PokemonDataController.getInstance(pokeApiService)

    // Llamada asíncrona
    LaunchedEffect(pokemonId) {
        try {
            loading.value = true

            // Simula la llamada para obtener los datos del Pokémon
            pokemon.value = pokemonId?.let { pokemonDataController.getPokemon(it) }
            specie.value = pokemonId?.let { pokemonDataController.getSpecies(it) }

            loading.value = false
        } catch (e: Exception) {
            loading.value = false
        }
    }

    // Cargo datos de los pokemons
    val types =  pokemon.value?.types
    val typeOne = if (!types.isNullOrEmpty() && types.isNotEmpty()) types[0].name else null
    val typeTwo = if (!types.isNullOrEmpty() && types.size > 1) types[1].name else null

    val typeOneCard = getTypeCardSafe(typeOne)
    val typeTwoCard = getTypeCardSafe(typeTwo)

    val weightInKg = (pokemon.value?.weight ?: -9999) / 10.0
    val heightInMeters = (pokemon.value?.height ?: -9999) / 10.0

    val formattedWeight = if (weightInKg >= 0) String.format("%.1f kg", weightInKg) else "N/A"
    val formattedHeight = if (heightInMeters >= 0) String.format("%.1f m", heightInMeters) else "N/A"

    val description = (specie.value?.flavorText?.get(0) ?: "N/A").toString().replace("\n", " ")


    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        TopView(
            name = (pokemon.value?.name ?: "unown").replaceFirstChar { it.uppercase(Locale.getDefault()) },
            id = (pokemon.value?.id ?: -9999).toString(),
            sprites = pokemon.value?.sprites,
            typeOneCard = typeOneCard,
            typeTwoCard = typeTwoCard
        )
        InfoSlider(
            listOf(
                {
                    BasicInfo(
                        weight = formattedWeight,
                        height = formattedHeight,
                        description = description,
                        listOf(
                            painterResource(R.drawable.bulbasaur),
                            painterResource(R.drawable.bulbasaur),
                            painterResource(R.drawable.bulbasaur)))
                },
                {
                    Estadisticas(
                        "45",
                        "49",
                        "49",
                        "65",
                        "65",
                        "40",
                        "305"
                    )
                }
            )
        )
    }
}

@Composable
fun TopView(
    name: String,
    id: String,
    sprites: Sprites?,
    typeOneCard: @Composable () -> Unit,
    typeTwoCard: @Composable () -> Unit?
) {
    // Obtén los colores del tema actual
    val textColor = MaterialTheme.colorScheme.onPrimary
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(end = 2.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor
                    )
                    Text(
                        text = id,
                        modifier = Modifier
                            .padding(end = 2.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = textColor // Usar color secundario del tema para el ID
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Imágenes de los tipos

                    typeOneCard.invoke()
                    typeTwoCard.invoke()

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.stars_shiny),
                        contentDescription = "stars",
                        modifier = Modifier
                            .width(28.dp)
                            .padding(end = 5.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.stars_shiny),
                        contentDescription = "stars",
                        modifier = Modifier
                            .width(28.dp)
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = primaryColor
                    )
                ) {
                    ImageSlider(
                        images = sprites?.normalSprites ?: listOf("")
                    )
                }
            }
        }
    }
}

@Composable
fun ImageSlider(
    images: List<String>
) {
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                HorizontalPager(
                    count = images.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondary)
                ) { page ->

                    val painter = rememberImagePainter(
                        images[page],
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.placeholder_ditto)
                            error(R.drawable.error_unown)
                        }
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Image ${page + 1}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun InfoSlider(
    views: List<@Composable () -> Unit>
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val iconRes = listOf(
        R.drawable.icon_info,
        R.drawable.icon_sword,
        R.drawable.icon_graph,
        R.drawable.icon_plus
    )

    Column {
        // Indicadores con íconos
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            iconRes.forEachIndexed { index, iconRes ->
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(iconRes),
                        contentDescription = "Page $index",
                        modifier = Modifier
                            .size(40.dp) // Ajusta el tamaño del ícono
                            .background(
                                color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                                shape = CircleShape
                            )
                            .padding(8.dp) // Espaciado interno opcional
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                count = views.size,
                state = pagerState
            ) { page ->
                views[page]()
            }
        }
    }
}


@Composable
fun BasicInfo(
    weight: String,
    height: String,
    description: String,
    evolutions: List<Painter>){
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ){
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(12.dp),
            text = "Descripción",
        )
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(12.dp),
            text = description
        )
        ElevatedCard (
            modifier = Modifier.padding(12.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        )
        {
            Row {
                Text(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(12.dp),
                    text = "Weight $weight"
                )
                Image(
                    painter = painterResource(R.drawable.weight),
                    contentDescription = "weight",
                    modifier = Modifier
                        .padding(16.dp,12.dp)
                        .height(24.dp)
                )
            }
        }
        ElevatedCard (
            modifier = Modifier.padding(12.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        )
        {
            Row {
                Text(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(12.dp),
                    text = "Height $height"
                )
                Image(
                    painter = painterResource(R.drawable.height),
                    contentDescription = "height",
                    modifier = Modifier
                        .padding(16.dp,12.dp)
                        .height(24.dp)
                )
            }
        }
        ElevatedCard(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Alinea los elementos horizontalmente en el centro
            ) {
                items(evolutions.size) { i ->
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally // Alinea el contenido dentro de cada columna al centro
                    ) {
                        Image(
                            painter = evolutions[i],
                            contentDescription = "Evolution",
                            modifier = Modifier
                                .padding(12.dp)
                                .height(52.dp)
                        )
                        Text(
                            text = "[nombrePokemon?]",
                            modifier = Modifier.padding(top = 4.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    PMDM_Pokedex_ComposableTheme {
//        Column (
//            modifier = Modifier
//                .fillMaxSize()
//        ){
//            TopView(
//                name = "Bulbasaur",
//                id = "#$001",
//                pokemonImage = painterResource(R.drawable.bulbasaur),
//                imgType01 = painterResource(R.drawable.type_grass),
//                imgType02 = painterResource(R.drawable.type_poison)
//            )
//
//            Column (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//            ){
//                ImageSlider(listOf(painterResource(R.drawable.bulbasaur),painterResource(R.drawable.type_poison), painterResource(R.drawable.type_grass)),)
//
//                InfoSlider(
//                    listOf(
//                        {
//                            BasicInfo(
//                                "123",
//                                "321",
//                                listOf(
//                                    painterResource(R.drawable.bulbasaur),
//                                    painterResource(R.drawable.bulbasaur),
//                                    painterResource(R.drawable.bulbasaur)))
//                        },
//                        {
//                            Estadisticas(
//                                "45",
//                                "49",
//                                "49",
//                                "65",
//                                "65",
//                                "40",
//                                "305"
//                            )
//                        }
//                    )
//                )
//            }
//        }
//    }
//}