package com.example.pmdm_pokedex_composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.tooling.preview.Preview

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
        MainPanel()
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
                    composable("Pokedex") { CardList(navController) }

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
    val coroutineScope = rememberCoroutineScope()
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet() {
            NavigationDrawerItem(
                label = { Text(text = "Pokedex") },
                selected = false,
                onClick = {
                    navController.navigate("Pokedex")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(6.dp),
            )
            NavigationDrawerItem(
                label = { Text(text = "Movedex") },
                selected = false,
                onClick = {
                    navController.navigate("Movedex")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(6.dp),
            )
            HorizontalDivider(modifier = Modifier.padding(6.dp))
            NavigationDrawerItem(
                label = { Text(text = "Settings") },
                selected = false,
                onClick = {},
                modifier = Modifier.padding(6.dp),
            )
        } }
    ) {
        content()
    }

//    ModalDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            DrawerContent(navController, drawerState)
//        }
//    ) {
//        Scaffold(
//            floatingActionButtonPosition = FabPosition.End,
//            floatingActionButton = {
//                FloatingActionButton(
//                    backgroundColor = MaterialTheme.colorScheme.tertiary,
//                    onClick = {
//                        scope.launch {
//                            if (drawerState.isClosed) {
//                                drawerState.open()
//                            } else {
//                                drawerState.close()
//                            }
//                        }
//                    }
//                ) {
//                    Icon(Icons.Default.Menu, contentDescription = "Open menu")
//                }
//            }
//        ) { paddingValues ->
//            Box(modifier = Modifier.padding(paddingValues)) {
//                content()
//            }
//        }
//    }
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
                    drawerState.close()
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
                    drawerState.close()
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
