package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import com.example.pmdm_pokedex_composable.model.data_classes.Move
import com.example.pmdm_pokedex_composable.model.data_classes.pokeApiService
import kotlinx.coroutines.launch
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveDex(
    drawerState: DrawerState,
    pokemonDataController: PokemonDataController
) {
    val movesList = remember { mutableStateListOf<String>() }
    val loading = remember { mutableStateOf(true) }
    val selectedMove = remember { mutableStateOf<String?>(null) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        try {
            loading.value = false
            val moveDex = pokemonDataController.getMovesList()
            movesList.addAll(moveDex.map { it.name })
            loading.value = true
        } catch (e: Exception) {
            loading.value = false
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            selectedMove.value?.let { moveName ->
                MoveDetails(
                    moveName = moveName,
                    pokemonDataController = pokemonDataController
                )
            }
        },
        sheetContainerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Scaffold(
            topBar = {
                TopBar(
                    drawerState = drawerState,
                    title = "MoveDex",
                    actions = {
                        IconButton(onClick = { /* Acción de filtrar */ }) {
                            Icon(Icons.Filled.FilterList, contentDescription = "Filter")
                        }
                    }
                )
            },
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                SearchBarMoves(
                    list = movesList,
                    card = { moveName ->
                        MoveCard(
                            name = moveName,
                            onClick = {
                                coroutineScope.launch {
                                    selectedMove.value = moveName
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MoveCard(
    name: String,
    onClick: () -> Unit) {


    val loading = remember { mutableStateOf(true) }
    val move = remember { mutableStateOf<Move?>(null) }
    val pokemonDataController = PokemonDataController.getInstance(pokeApiService)

    // Llamada asíncrona
    LaunchedEffect(name) {
        try {
            loading.value = true

            // Simula la llamada para obtener los datos del Pokémon
            move.value = pokemonDataController.getMove(name)

            loading.value = false
        } catch (e: Exception) {
            loading.value = false
        }
    }

    val power = move.value?.power
    val accuracy = move.value?.accuracy
    val pp = move.value?.pp
    val type = move.value?.type

    val damageClass = move.value?.damageClass
    val typeColor = type?.let { PokemonType.fromName(it) }
    val typeCard = getTypeCardSafe(type)


    ElevatedCard(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .border(
                width = 2.dp,
                color = typeColor?.color ?: PokemonType.NORMAL.color,
                shape = RoundedCornerShape(8.dp)
            ),
        onClick = {
            onClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(lightenColor(typeColor?.color ?: PokemonType.NORMAL.color, 0.5f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = name.replaceFirstChar { it.uppercase(Locale.getDefault()) },
                            color = darkenColor(typeColor?.color ?: PokemonType.NORMAL.color, 0.6f)
                        )
                        Text(
                            text = "Power: $power",
                            color = darkenColor(typeColor?.color ?: PokemonType.NORMAL.color, 0.6f)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Accuracy: $accuracy",
                            color = darkenColor(typeColor?.color ?: PokemonType.NORMAL.color, 0.6f)
                        )

                        Text(
                            text = "PP: $pp",
                            color = darkenColor(typeColor?.color ?: PokemonType.NORMAL.color, 0.6f)
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {

            typeCard.invoke()

            when(damageClass){
                "physical"-> PhysicalCard()
                "special"-> SpecialCard()
                "status"-> StatusCard()
            }
        }
    }
}

@Composable
fun MoveDetails(
    moveName: String,
    pokemonDataController: PokemonDataController
) {
    val move = remember { mutableStateOf<Move?>(null) }
    val loading = remember { mutableStateOf(true) }

    LaunchedEffect(moveName) {
        try {
            loading.value = true
            move.value = pokemonDataController.getMove(moveName)
            loading.value = false
        } catch (e: Exception) {
            loading.value = false
        }
    }

    if (loading.value) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {

        val power = move.value?.power
        val accuracy = move.value?.accuracy
        val pp = move.value?.pp
        val flavorText = move.value?.flavorText
        val effect = move.value?.shortEffect
        val type = move.value?.type

        val damageClass = move.value?.damageClass
        val typeColor = type?.let { PokemonType.fromName(it) }
        val typeCard = getTypeCardSafe(type)

        move.value?.let {
            Moves(
                typeColor = typeColor?.color ?: Color.Red,
                moveColor = 0xFF,
                name = moveName,
                type = damageClass ?: "null",
                typeCard = typeCard,
                power = power.toString(),
                accuracy = accuracy.toString(),
                pp = pp.toString(),
                priority = move.value?.priority.toString(),
                flavorText = flavorText ?: "null",
                effect = effect ?: "null"
            )
        }
    }
}
