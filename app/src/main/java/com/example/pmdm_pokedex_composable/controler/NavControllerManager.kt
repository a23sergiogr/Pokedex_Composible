package com.example.pmdm_pokedex_composable.controler

import androidx.navigation.NavController

object  NavControllerManager  {
    private var _navController: NavController? = null

    // Setter para inicializar el NavController
    fun setNavController(navController: NavController) {
        _navController = navController
    }

    // Getter para obtener el NavController
    fun getNavController(): NavController? {
        return _navController
    }
}