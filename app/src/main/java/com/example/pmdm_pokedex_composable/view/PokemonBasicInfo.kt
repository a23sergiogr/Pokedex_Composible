package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.controler.NavControllerManager
import java.util.Locale


@Composable
fun BasicInfo(
    weight: String,
    height: String,
    description: String,
    evolutions: List<Evolution>,
    textColor: Color,
    complementaryColor: Color
){
    Column (
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ){
        Text(
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp),
            text = description
        )
        ElevatedCard (
            modifier = Modifier.padding(12.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = complementaryColor
            )
        )
        {
            Row(
                modifier = Modifier.background(Color.Transparent)
            ) {
                Text(
                    color = darkenColor(complementaryColor, 0.8f),
                    modifier = Modifier.padding(12.dp),
                    text = "Weight $weight"
                )
                Image(
                    painter = painterResource(R.drawable.weight),
                    contentDescription = "weight",
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(16.dp,12.dp)
                        .height(24.dp)
                )
            }
        }
        ElevatedCard (
            modifier = Modifier.padding(12.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = complementaryColor
            )
        )
        {
            Row {
                Text(
                    color = darkenColor(complementaryColor, 0.8f),
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
                containerColor = complementaryColor
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