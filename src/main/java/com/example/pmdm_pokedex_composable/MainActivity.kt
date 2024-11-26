package com.example.pmdm_pokedex_composable

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDM_Pokedex_ComposableTheme {
                mainPanel()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMDM_Pokedex_ComposableTheme {
        //DrawerExample(navController = rememberNavController())
        cardListTest()
    }
}

@Composable
fun mainPanel() {
    val systemUiController = rememberSystemUiController()

    // Determina si el color de fondo (background) es claro u oscuro
    val isStatusBarLight = MaterialTheme.colorScheme.background.luminance() > 0.5
    val isNavBarLight = MaterialTheme.colorScheme.surface.luminance() > 0.5

    // Configurar barra de estado con el color de fondo (background)
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background, // Usar color de fondo
        darkIcons = isStatusBarLight // Iconos oscuros si el fondo es claro
    )

    // Configurar barra de navegación con el color de superficie (surface)
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.surface, // O usa onBackground si prefieres
        darkIcons = isNavBarLight // Iconos oscuros si el fondo es claro
    )

    // Contenido principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // Maneja automáticamente las barras de sistema
    ) {
        cardListTest()
    }
}


/**
 * Diseño Ejemplo de una targeta con la
 * infórmación más básica de un Pokemon
 */
@Composable
fun ElevatedCardExample() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
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
                        text = "Bulbasaur",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "#0001",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                    )
                    Image(
                        painter = painterResource(R.drawable.stars_shiny),
                        contentDescription = "stars",
                        modifier = Modifier.width(12.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.type_grass),
                        contentDescription = "type_grass",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp)
                            .size(50.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.type_poison),
                        contentDescription = "type_poison",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 2.dp, 0.dp)
                            .size(50.dp)
                    )
                }
            }

            Image(
                painter = painterResource(R.drawable.bulbasaur),
                contentDescription = "bulbasaur",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun PokedexCard(
    name: String,
    id: String,
    pokemonImage: Painter,
    imgType01: Painter,
    imgType02: Painter?
) {
    // Obtén los colores del tema actual
    val textColor = MaterialTheme.colorScheme.onPrimary
    val primaryColor = MaterialTheme.colorScheme.primary

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = primaryColor // Aplicar el color de fondo del tema
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
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
                    Image(
                        painter = painterResource(R.drawable.stars_shiny),
                        contentDescription = "stars",
                        modifier = Modifier.width(24.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                }
            }

            Image(
                painter = pokemonImage,
                contentDescription = name,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            )
        }
    }
}



@Composable
fun cardListTest(){
    LazyColumn (
        Modifier.background(MaterialTheme.colorScheme.background)
    ){
        items(1000) { i ->
            PokedexCard(
                name = "Bulbasaur",
                id = "#$i",
                pokemonImage = painterResource(R.drawable.bulbasaur),
                imgType01 = painterResource(R.drawable.type_grass),
                imgType02 = painterResource(R.drawable.type_poison)
            )
        }
    }
}

//Código de prueba sin uso aparente

/**
 *
 * //                val navController = rememberNavController()
 * //                Scaffold(
 * //                    topBar = {
 * //                        DrawerExample(navController)
 * //                    }
 * //                ) { innerPadding ->
 * //                    Greeting(
 * //                        name = "Android",
 * //                        modifier = Modifier.padding(innerPadding)
 * //                    )
 * //                }
 */
@Composable
fun CustomTopNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.weight(1f), onClick = {
                navController.navigate("info")
            }) {
            Image(painter = painterResource(R.drawable.icon_info), contentDescription = "Info")
        }
        IconButton(
            modifier = Modifier.weight(1f), onClick = {
                navController.navigate("sword")
            }) {
            Image(painter = painterResource(R.drawable.icon_sword), contentDescription = "Sword")
        }
        IconButton(
            modifier = Modifier.weight(1f), onClick = {
                navController.navigate("graph")
            }) {
            Image(painter = painterResource(R.drawable.icon_graph), contentDescription = "Graph")
        }
        IconButton(
            modifier = Modifier.weight(1f), onClick = {
                navController.navigate("plus")
            }) {
            Image(painter = painterResource(R.drawable.icon_plus), contentDescription = "Plus")
        }
    }
}

@Composable
fun NavigationGraph(navController: NavController) {
    // Definimos las pantallas dentro del NavHost
    NavHost(navController = navController as NavHostController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    DrawerExample(navController)
}

@Composable
fun ProfileScreen(navController: NavController) {
    Text("Pantalla de perfil")
}

@Composable
fun SettingsScreen(navController: NavController) {
    Text("Pantalla de ajustes")
}

@Composable
fun DrawerExample(navController: NavController) {
    // Aquí va la implementación de tu DrawerExample
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Aquí defines el contenido del menú lateral (drawer)
            DrawerContent(navController, drawerState, coroutineScope)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drawer Example") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Contenido principal", modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Opción 1 - Inicio
        Text(
            text = "Inicio",
            modifier = Modifier
                .clickable {
                    navController.navigate("home")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
                .padding(16.dp)
        )

        // Opción 2 - Perfil
        Text(
            text = "Perfil",
            modifier = Modifier
                .clickable {
                    navController.navigate("profile")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
                .padding(16.dp)
        )

        // Opción 3 - Ajustes
        Text(
            text = "Ajustes",
            modifier = Modifier
                .clickable {
                    navController.navigate("settings")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
                .padding(16.dp)
        )
    }
}
