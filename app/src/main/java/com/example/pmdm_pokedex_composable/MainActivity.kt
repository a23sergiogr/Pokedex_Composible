package com.example.pmdm_pokedex_composable

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDM_Pokedex_ComposableTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        DrawerExample(navController)
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMDM_Pokedex_ComposableTheme {
        DrawerExample(navController = rememberNavController())
    }
}

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

fun BlendColors(color1: Color, color2: Color): Color {
    // Promediamos los valores de los componentes RGB y A
    return Color(
        red = (color1.red + color2.red) / 2,
        green = (color1.green + color2.green) / 2,
        blue = (color1.blue + color2.blue) / 2,
        alpha = (color1.alpha + color2.alpha) / 2 // Promediamos la transparencia (alpha)
    )
}