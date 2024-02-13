package com.example.app.scaffoldnavapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    ScaffoldNavAppScreen("Main Screen", "This is the main screen", navController = navController)
}

@Composable
fun InfoScreen(navController: NavController) {
    ScaffoldNavAppScreen("Info Screen", "This is the info screen", navController = navController)
}

@Composable
fun SettingsScreen(navController: NavController) {
    ScaffoldNavAppScreen("Settings Screen", "This is the settings screen", navController = navController)
}