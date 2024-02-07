package com.example.app.calories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun IntensityList(
    onClick: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIntensity by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val intensityOptions = listOf("Light", "Moderate", "Intense", "Very Intense")

    val icon =
        if (expanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

    Column (modifier = modifier) {
        Text(text = "Activity intensity:", modifier = Modifier.padding(horizontal = 8.dp))
        OutlinedTextField(
            readOnly = true,
            value = selectedIntensity,
            onValueChange = { selectedIntensity = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to the DropdownMenu the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = "Select intensity") },
            trailingIcon = {
                Icon(
                    icon, "Intensity level",
                    Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            intensityOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedIntensity = label

                        val intensity: Float =
                            when (label) {
                                "Light" -> 1.35f
                                "Moderate" -> 1.55f
                                "Intense" -> 1.75f
                                "Very Intense" -> 2.2f
                                else -> 0.0f
                            }

                        onClick(intensity)
                        expanded = false
                    }
                )
            }
        }
    }
}


