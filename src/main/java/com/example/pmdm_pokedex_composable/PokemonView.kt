package com.example.pmdm_pokedex_composable

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.google.accompanist.pager.*


@Composable
fun PokemonView(){

}

@Composable
fun topView(
    name: String,
    id: String,
    pokemonImage: Painter,
    imgType01: Painter,
    imgType02: Painter?
) {
    // Obtén los colores del tema actual
    val textColor = Color.Red
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
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

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.stars_shiny),
                    contentDescription = "stars",
                    modifier = Modifier
                        .width(28.dp)
                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
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

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(0.dp, 120.dp, 0.dp, 0.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = primaryColor
            )
        ) {
            ImageSlider(
                listOf(painterResource(R.drawable.bulbasaur),painterResource(R.drawable.type_poison), painterResource(R.drawable.type_grass)),
            )
        }
    }
}

@Composable
fun ImageSlider(
    images: List<Painter>
) {
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
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

@Composable
fun InfoSlider(
    view: List<@Composable () -> Unit>
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            repeat(view.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(4.dp)
                        .background(
                            color = if (pagerState.currentPage == index) Color.Black else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                count = view.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                view[page]()
            }
        }
    }
}

@Composable
fun BasicInfo(weight: String, height: String, evolutions: List<Painter>){
    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
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
        topView(
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