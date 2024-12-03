package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.controler.NavControllerManager
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import com.example.pmdm_pokedex_composable.model.data_classes.EvolutionChain
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.example.pmdm_pokedex_composable.model.data_classes.pokeApiService
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun PokemonView(
    pokemonId: String?
){
    val loading = remember { mutableStateOf(true) }
    val pokemon = remember { mutableStateOf<Pokemon?>(null) }
    val specie = remember { mutableStateOf<Species?>(null) }
    val evolutionChain = remember { mutableStateOf<EvolutionChain?>(null) }
    val evolutionImages = remember { mutableStateOf<List<Evolution>>(emptyList()) } // Para almacenar las imágenes de la cadena evolutiva
    val pokemonDataController = PokemonDataController.getInstance(pokeApiService)

// Llamada asíncrona
    LaunchedEffect(pokemonId) {
        try {
            loading.value = true
            pokemon.value = pokemonId?.let { pokemonDataController.getPokemon(it) }
            specie.value = pokemonId?.let { pokemonDataController.getSpecies(it) }

            // Obtener la cadena evolutiva
            evolutionChain.value = specie.value?.evolutionChainURL?.let {
                pokemonDataController.getEvolutionChain(it)
            } ?: EvolutionChain(listOf(NamedURLs("error", "error")))

            // Ahora obtenemos las imágenes de la cadena evolutiva usando los nombres
            val pokemonNames = evolutionChain.value?.evolutionChain?.map { it.name } ?: emptyList()

            val images = pokemonNames.mapNotNull { name ->
                try {
                    // Obtener el Pokémon desde el nombre y extraer el sprite
                    val evolutionPokemon = pokemonDataController.getPokemon(name)

                    // Verificamos que la propiedad sprites y normalSprites no sean nulas
                    val spriteUrl = evolutionPokemon?.sprites?.normalSprites?.get(0)
                    if (spriteUrl != null) {
                        // Crear un objeto Evolution con el nombre y la URL de la imagen
                        Evolution(name = name, imgUrl = spriteUrl)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    // Si hay un error al obtener los datos o procesarlos, simplemente lo ignoramos y retornamos null
                    null
                }
            }

            evolutionImages.value = images // Guardar las imágenes en la lista de evoluciones

            loading.value = false
        } catch (e: Exception) {
            loading.value = false
        }
    }

    // Cargo datos de los pokemons
    val types = pokemon.value?.types
    val typeOne = if (!types.isNullOrEmpty() && types.isNotEmpty()) types[0].name else null
    val typeTwo = if (!types.isNullOrEmpty() && types.size > 1) types[1].name else null

    val typeOneCard = getTypeCardSafe(typeOne)
    val typeTwoCard = getTypeCardSafe(typeTwo)

    val weightInKg = (pokemon.value?.weight ?: -9999) / 10.0
    val heightInMeters = (pokemon.value?.height ?: -9999) / 10.0

    val formattedWeight = if (weightInKg >= 0) String.format("%.1f kg", weightInKg) else "N/A"
    val formattedHeight = if (heightInMeters >= 0) String.format("%.1f m", heightInMeters) else "N/A"

    val description = (specie.value?.flavorText?.get(0) ?: "N/A").toString().replace("\n", " ")

    val color = Color(PokemonColor.fromName(specie.value?.color.toString())?.hexCode ?: PokemonColor.NULL.hexCode)
    val textColor = darkenColor(color, 0.8f)

    val ps = pokemon.value?.stats?.get(0)?.baseStat ?: -1
    val attack = pokemon.value?.stats?.get(1)?.baseStat ?: -1
    val defence = pokemon.value?.stats?.get(2)?.baseStat ?: -1
    val spaAttack = pokemon.value?.stats?.get(3)?.baseStat ?: -1
    val spaDefence = pokemon.value?.stats?.get(4)?.baseStat ?: -1
    val speed = pokemon.value?.stats?.get(5)?.baseStat ?: -1

    val total = ps + attack + defence + spaAttack + spaDefence + speed

    setUIColors(darkenColor(color, 0.2f))


    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        color,
                        tranparentColor(color)
                    )
            ))
    ){
        TopView(
            name = (pokemon.value?.name ?: "unown").replaceFirstChar { it.uppercase(Locale.getDefault()) },
            id = (pokemon.value?.id ?: -9999).toString(),
            sprites = pokemon.value?.sprites,
            typeOneCard = typeOneCard,
            typeTwoCard = typeTwoCard,
            textColor = textColor,
            pokemon = pokemon
        )
        InfoSlider(
            listOf(
                {
                    BasicInfo(
                        weight = formattedWeight,
                        height = formattedHeight,
                        description = description,
                        evolutionImages.value,
                        textColor = textColor,
                    )
                },
                {
                    PokemonCombatView(
                        types = listOf(typeOne.toString(), typeTwo.toString())
                    )
                },
                {
                    Estadisticas(
                        ps = ps.toString(),
                        attack = attack.toString(),
                        defence = defence.toString(),
                        spaAttack = spaAttack.toString(),
                        spaDefence = spaDefence.toString(),
                        speed = speed.toString(),
                        total = total.toString(),
                        textColor = textColor
                    )

                },
                { PokemonMoveList(
                    movesList = pokemon.value?.moves ?: listOf(NamedURLs("tackle", "tackle")),
                    )
                },
            ),
            color = color
        )
    }
}

@Composable
fun TopView(
    name: String,
    id: String,
    sprites: Sprites?,
    typeOneCard: @Composable () -> Unit,
    typeTwoCard: @Composable () -> Unit?,
    textColor: Color,
    pokemon: MutableState<Pokemon?>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
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
                        color = textColor
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
                    Box(
                        modifier = Modifier
                            .width(75.dp)
                            .height(30.dp)
                            .padding(end = 4.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        typeOneCard.invoke()
                    }

                    Box(
                        modifier = Modifier
                            .width(75.dp)
                            .height(30.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        typeTwoCard.invoke()
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Botón de sonido
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically) // Asegura alineación vertical
                    ) {
                        PlaySoundButton(
                            pokemon.value?.cries
                                ?: "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/legacy/132.ogg"
                        )
                    }
//                    Image(
//                        painter = painterResource(R.drawable.stars_shiny),
//                        contentDescription = "stars",
//                        modifier = Modifier
//                            .width(28.dp)
//                            .padding(5.dp, 0.dp, 0.dp, 0.dp),
//                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                ImageSlider(
                    images = sprites?.normalSprites ?: listOf("")
                )
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
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) { page ->

            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(images[page]).apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    placeholder(R.drawable.placeholder_ditto)
                    error(R.drawable.error_unown)
                }).build()
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

@Composable
fun InfoSlider(
    views: List<@Composable () -> Unit>,
    color: Color
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val iconRes = listOf(
        Icons.Default.Info,
        Icons.Default.Shield,
        Icons.Default.AutoGraph,
        Icons.Default.Extension
    )

    Column {
        // Fila de íconos con bordes y brillo en el ícono seleccionado
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            iconRes.forEachIndexed { index, iconRes ->
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp) // Espaciado entre los íconos
                        .clip(CircleShape) // Hacer los íconos circulares
                        .background(
                            color = if (pagerState.currentPage == index) {
                                darkenColor(color.copy(alpha = 0.6f), 0.2f)
                            } else {
                                lightenColor(color.copy(alpha = 0.8f), 0.2f)
                            },
                            shape = CircleShape
                        )
                        .border(
                            width = 2.dp,
                            color = if (pagerState.currentPage == index) {
                                darkenColor(color.copy(alpha = 0.6f), 0.2f)
                            } else {
                                lightenColor(color.copy(alpha = 0.6f), 0.2f)
                            },
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 8.dp,
                            shape = CircleShape,
                            ambientColor = color.copy(alpha = 0.2f),
                            spotColor = color.copy(alpha = 0.4f)
                        )
                ) {
                    Icon(
                        imageVector = iconRes,
                        contentDescription = "Page $index",
                        modifier = Modifier
                            .size(40.dp) // Tamaño del ícono
                            .padding(8.dp) // Espaciado interno
                    )
                }
            }
        }

        // Caja para mostrar las vistas de la página
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp) // Ajuste para no solapar el contenido
        ) {
            HorizontalPager(
                count = views.size,
                state = pagerState
            ) { page ->
                views[page]() // Mostrar la vista de cada página
            }
        }
    }
}



@Composable
fun BasicInfo(
    weight: String,
    height: String,
    description: String,
    evolutions: List<Evolution>,
    textColor: Color
    ){
    Column (
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 8.dp)
            .fillMaxSize()
    ){
        Text(
            color = textColor,
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
                horizontalArrangement = Arrangement.Center
            ) {
                items(evolutions.size) { i ->

                    val painter = rememberImagePainter(
                        evolutions[i].imgUrl,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.placeholder_ditto)
                            error(R.drawable.error_unown)
                        }
                    )

                    val navController = NavControllerManager.getNavController()

                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "Evolution",
                            modifier = Modifier
                                .padding(12.dp)
                                .size(72.dp)
                                .clickable { navController?.navigate("PokemonView/${evolutions[i].name}") }
                        )
                        Text(
                            text = evolutions[i].name.replaceFirstChar { it.uppercase(Locale.getDefault()) },
                            modifier = Modifier.padding(top = 4.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}


data class Evolution(
    val name: String,
    val imgUrl: String
)