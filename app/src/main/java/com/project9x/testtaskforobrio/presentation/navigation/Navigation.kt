package com.project9x.testtaskforobrio.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project9x.testtaskforobrio.presentation.screens.first.FirstScreen
import com.project9x.testtaskforobrio.presentation.screens.second.SecondScreen

enum class NavigationTree {
    FirstScreen, SecondScreen
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.FirstScreen.name) {
        composable(NavigationTree.FirstScreen.name) {
            FirstScreen(navController)
        }
        composable(NavigationTree.SecondScreen.name) {
            SecondScreen(navController)
        }

    }

}