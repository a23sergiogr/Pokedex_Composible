package com.example.pmdm_pokedex_composable.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.pmdm_pokedex_composable.R
import kotlinx.coroutines.launch



@Preview
@Composable
fun PreviewMoves(){
    PMDM_Pokedex_ComposableTheme {
//        Moves(
//            MaterialTheme.colorScheme.background,
//            0xFF95c799,
//            painterResource(R.drawable.type_grass),
//            "Gigadrenado",
//            "special",
//            "75",
//            "100",
//            "15",
//            "0",
//            "Steals 1/2 of the damage inflicted",
//            "Inflicts regular damage. Drains half the damage inflicted to heal the user"
//        )


        BottomSheetWithMoves()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithMoves() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
                Moves(
                    MaterialTheme.colorScheme.background,
                    0xFF95c799,
                    painterResource(R.drawable.type_grass),
                    "Gigadrenado",
                    "special",
                    "75",
                    "100",
                    "15",
                    "0",
                    "Steals 1/2 of the damage inflicted",
                    "Inflicts regular damage. Drains half the damage inflicted to heal the user"
                )
        },
        sheetContainerColor = Color(0xFF95c799)
    ) {
        //Content of the Screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }) {
                Text("Abrir hoja con Gigadrenado")
            }
        }
    }
}