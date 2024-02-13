package com.example.app.scaffoldnavapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val menuOptions = listOf("Info", "Settings")
    val menuIcons = listOf(Icons.Outlined.Info, Icons.Outlined.Settings)
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )},
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
                if (navController.previousBackStackEntry == null) {
                    IconButton(
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        menuOptions.forEach{ option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    navController.navigate(option)
                                    expanded = false
                                },
                                leadingIcon = { Icon(menuIcons[menuOptions.indexOf(option)], contentDescription = option) }
                            )
                        }
                    }
                }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
    )
}