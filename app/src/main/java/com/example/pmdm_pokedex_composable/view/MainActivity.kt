package com.example.pmdm_pokedex_composable.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pmdm_pokedex_composable.ui.theme.PMDM_Pokedex_ComposableTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pmdm_pokedex_composable.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.*
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.pmdm_pokedex_composable.model.data_classes.pokeApiService


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

    // Determina si el color de fondo es claro u oscuro
    val isStatusBarLight = MaterialTheme.colorScheme.background.luminance() > 0.5
    val isNavBarLight = MaterialTheme.colorScheme.surface.luminance() > 0.5

    // Configurar colores de la barra de estado y navegación
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background,
        darkIcons = isStatusBarLight
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.surface,
        darkIcons = isNavBarLight
    )

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        LateralMenu(
            drawerState = drawerState,
            navController = navController,
            content = {
                NavHost(
                    navController = navController,
                    startDestination = "Pokedex"
                ) {
                    composable("Pokedex") {
                        Pokedex(
                            drawerState = drawerState,
                            navController = navController,
                            pokeApiService = pokeApiService
                        )
                    }
                    composable("MoveDex") {
                        MoveDex(
                            drawerState = drawerState,
                        )
                    }
                    composable("PokemonView") {
                        //PokemonView()
                    }
                }
            }
        )
    }
}

@Composable
fun LateralMenu(
    navController: NavController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Colores del tema actual
    val colors = MaterialTheme.colorScheme

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .background(colors.background)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(colors.primary)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.drawer_image),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                HorizontalDivider(color = colors.surface, thickness = 1.dp)

                NavigationDrawerItem(
                    label = { Text(text = "Pokedex", color = colors.onSurface) },
                    selected = false,
                    onClick = {
                        navController.navigate("Pokedex")
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(colors.surface.copy(alpha = 0.1f))
                        .padding(8.dp)
                )
                NavigationDrawerItem(
                    label = { Text(text = "MoveDex", color = colors.onSurface) },
                    selected = false,
                    onClick = {
                        navController.navigate("MoveDex")
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(colors.surface.copy(alpha = 0.1f))
                        .padding(8.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = colors.outline
                )
                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = colors.onSurface) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .background(colors.surface.copy(alpha = 0.1f))
                        .padding(8.dp)
                )
            }
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSample(
    list: List<String>, // Lista sobre la que se hará la búsqueda
    contentBelowSearchBar: @Composable () -> Unit // Contenido adicional debajo de la barra de búsqueda
) {
    val textFieldState = rememberTextFieldState()
    val searchQuery = textFieldState.text

    // Filtrar los resultados según el texto de búsqueda
    val filteredList = list.filter { it.contains(searchQuery, ignoreCase = true) }

    Box(Modifier.fillMaxSize()) {
        // Barra de búsqueda siempre expandida
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    state = textFieldState,
                    onSearch = { },
                    expanded = true, // Mantener expandida la barra
                    onExpandedChange = { },
                    placeholder = { Text("Search Pokémon") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Filled.MoreVert, contentDescription = null) },
                )
            },
            expanded = true,
            onExpandedChange = { }, // No se necesita acción de expansión
            content = {
//                // Mostrar los resultados filtrados debajo de la barra de búsqueda
//                Column(Modifier.verticalScroll(rememberScrollState())) {
//                    filteredList.forEach { item ->
//                        Text(
//                            text = item,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                        )
//                    }
//                }

                // Componente adicional debajo de la barra de búsqueda
                contentBelowSearchBar()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    drawerState: DrawerState,
    actions: @Composable (() -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
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
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            actions?.invoke()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}