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
                primaryColor
            )
        }
    }
}

@Composable
fun ImageSlider(
    images: List<Painter>,
    primaryColor: Color
) {
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Column {
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

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            repeat(images.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (selected) 12.dp else 8.dp)
                        .background(if (selected) Color.White else Color.Gray, CircleShape)
                )
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
    }
}