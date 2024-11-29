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
import androidx.compose.runtime.rememberCoroutineScope
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.model.data_classes.Pokemon
import com.example.pmdm_pokedex_composable.model.data_classes.Species
import com.example.pmdm_pokedex_composable.model.data_classes.Sprites
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun PokemonView(
    pokemon: Pokemon,
    species: Species,
    sprites: Sprites
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        TopView(
            name = "Bulbasaur",
            id = "#$001",
            pokemonImage = painterResource(R.drawable.bulbasaur),
            imgType01 = painterResource(R.drawable.type_grass),
            imgType02 = painterResource(R.drawable.type_poison)
        )
        InfoSlider(
            listOf(
                {
                    BasicInfo(
                        "123",
                        "321",
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
    pokemonImage: Painter,
    imgType01: Painter,
    imgType02: Painter?
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
                        color = textColor // Usar color primario del tema
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
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Imágenes de los tipos
                    Image(
                        painter = imgType01,
                        contentDescription = "type01",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(80.dp)
                    )
                    if (imgType02 != null) {
                        Image(
                            painter = imgType02,
                            contentDescription = "type02",
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(80.dp)
                        )
                    }

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
                        listOf(painterResource(R.drawable.bulbasaur),painterResource(R.drawable.type_poison), painterResource(
                            R.drawable.type_grass
                        )),
                    )
                }
            }
        }
    }
}

@Composable
fun ImageSlider(
    images: List<Painter>
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
                    Image(
                        painter = images[page],
                        contentDescription = "Image ${page + 1}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
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
fun BasicInfo(weight: String, height: String, evolutions: List<Painter>){
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
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur volutpat venenatis quam non ullamcorper. Nulla facilisi. Vivamus lacinia mauris non consequat sagittis. "
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



@Preview(showBackground = true)
@Composable
fun Preview() {
    PMDM_Pokedex_ComposableTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            TopView(
                name = "Bulbasaur",
                id = "#$001",
                pokemonImage = painterResource(R.drawable.bulbasaur),
                imgType01 = painterResource(R.drawable.type_grass),
                imgType02 = painterResource(R.drawable.type_poison)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ){
                ImageSlider(listOf(painterResource(R.drawable.bulbasaur),painterResource(R.drawable.type_poison), painterResource(R.drawable.type_grass)),)

                InfoSlider(
                    listOf(
                        {
                            BasicInfo(
                                "123",
                                "321",
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
    }
}