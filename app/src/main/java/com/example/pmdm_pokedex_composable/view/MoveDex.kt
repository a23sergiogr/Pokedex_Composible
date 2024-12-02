package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.R
import com.example.pmdm_pokedex_composable.controler.PokemonDataController
import com.example.pmdm_pokedex_composable.model.data_classes.Pokedex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MoveDex(
    drawerState: DrawerState,
    pokemonDataController: PokemonDataController
    ){
    val movesList = remember { mutableStateListOf<MovesData>() }
    val loading = remember { mutableStateOf(true) }
    var pokedex: Pokedex

    LaunchedEffect(Unit) {
        try {
            loading.value = false

            pokedex = pokemonDataController.getPokedex()

            pokedex.pokemonEntries.forEach {
                movesList.add(
                    MovesData(
                        ""
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
                actions = {
                    IconButton(onClick = { /* Acción de filtrar */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SearchBarMoves(
                list = movesList, // lista de objetos Pokemon
                card = { pokemon ->
//                    MoveCard(
//                        pokemonCardData = pokemon,
//                        pokemonDataController = pokemonDataController,
//                        navController = navController
//                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetMoveDex(
    content: @Composable () -> Unit
){
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            content()
        },
        sheetContainerColor = Color(0xFF95c799)
    ) {
        MoveCardList(coroutineScope, scaffoldState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveCardList(
    coroutineScope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState
){
    LazyColumn  (
        Modifier
            .background(MaterialTheme.colorScheme.background)
    ){
        items(100) { i ->
            MoveCard(
                "gigadrenado",
                0xFF95c799,
                "60",
                "100",
                "15",
                painterResource(R.drawable.type_grass),
                "special",
                coroutineScope,
                scaffoldState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveCard(
    name: String,
    typeColor: Long,
    power: String,
    accuracy: String,
    pp: String,
    imgType: Painter,
    typo: String,
    coroutineScope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState
    ){
    ElevatedCard(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .border(
                width = 2.dp,
                color = Color(typeColor),
                shape = RoundedCornerShape(8.dp)
            ),
        onClick = {
            coroutineScope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(typeColor / 2))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = name
                    )
                    Text(
                        text = "Power: $power"
                    )

                    Text(
                        text = "Accuracy: $accuracy"
                    )

                    Text(
                        text = "PP: $pp"
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = imgType,
                    contentDescription = "type01",
                    modifier = Modifier
                        .height(60.dp)
                        .width(140.dp)
                )

                when(typo){
                    "physical"-> PhysicalCard()
                    "special"-> SpecialCard()
                    "status"-> StatusCard()
                }
            }
        }
    }
}

@Composable
fun Moves(
    typeColor: Color,
    moveColor: Long,
    imgType: Painter,
    name: String,
    typo: String,
    power: String,
    accuracy: String,
    pp: String,
    priority: String,
    flavorText: String,
    effect: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(typeColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }

            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = imgType,
                    contentDescription = "type01",
                    modifier = Modifier
                        .size(140.dp)
                )

                when(typo){
                    "physical"-> PhysicalCard()
                    "special"-> SpecialCard()
                    "status"-> StatusCard()
                }

            }
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Power",
                    )

                    Text(
                        text = power,
                    )


                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "PP",
                    )

                    Text(
                        text = pp,
                    )
                }
                Column {
                    Text(
                        text = "Accuracy",
                    )

                    Text(
                        text = accuracy,
                    )


                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Priority",
                    )

                    Text(
                        text = priority,
                    )
                }
            }

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor/2)
                ),
                modifier = Modifier.padding( horizontal = 16.dp, vertical = 26.dp),
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .fillMaxWidth(),
                    text = "Description",
                    color = Color(moveColor*2)
                )

                Text(
                    modifier = Modifier.padding(26.dp),
                    text = flavorText,
                )
            }

            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(moveColor/2)
                ),
                modifier = Modifier.padding( horizontal = 16.dp, vertical = 26.dp),
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .fillMaxWidth(),
                    text = "Effect",
                    color = Color(moveColor*2)
                )

                Text(
                    modifier = Modifier.padding(26.dp),
                    text = effect,
                )
            }
        }
    }
}

data class MovesData(
    val name: String
)