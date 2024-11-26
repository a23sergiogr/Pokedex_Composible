package com.example.pmdm_pokedex_composable

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                MainPanel()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMDM_Pokedex_ComposableTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                //DrawerContent()
            }
        ) {
            Scaffold(
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = "Open menu")
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    CardList()
                }
            }
        }
    }
}

@Composable
fun MainPanel() {
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

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        LateralMenu(
            navController = navController,
            content = {
                NavHost(
                    navController = navController,
                    startDestination = "Pokedex"
                ) {
                    composable("Pokedex") { CardList() }

                    composable("MoveDex") { MoveDex() }
                }
            }
        )
    }
}

@Composable
fun LateralMenu(
    navController: NavController,
    content: @Composable () -> Unit
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
        }
    ) {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    }
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Open menu")
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Menú",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        NavigationButton(
            text = "Pokedex",
            icon = Icons.Default.Menu,
            onClick = {
                navController.navigate("Pokedex")
                coroutineScope.launch {
                    drawerState.close() // Cierra el menú
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NavigationButton(
            text = "Movedex",
            icon = Icons.Default.Menu,
            onClick = {
                navController.navigate("Movedex")
                coroutineScope.launch {
                    drawerState.close() // Cierra el menú
                }
            }
        )
    }
}

@Composable
fun NavigationButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 18.sp
            )
        }
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
