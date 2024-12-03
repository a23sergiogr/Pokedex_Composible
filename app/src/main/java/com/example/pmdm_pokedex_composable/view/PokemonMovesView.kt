package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.model.data_classes.urlclasses.NamedURLs

@Composable
fun PokemonMovesView(
    movesList: List<NamedURLs>
) {
    val rememberedMoves = remember { movesList }

    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(24.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
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
