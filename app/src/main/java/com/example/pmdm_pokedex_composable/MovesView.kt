package com.example.pmdm_pokedex_composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
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