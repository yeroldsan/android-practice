package com.example.app.scaffoldnavapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScaffoldNavAppScreen(title: String, text: String, navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title, navController) },
        bottomBar = { MyBottomBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                repeat(10) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = it.toString() + " " + text.trimIndent(),
                    )
                }
            }
        }
    }

}