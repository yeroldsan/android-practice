package com.example.app.calories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.calories.ui.theme.CaloriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CaloriesScreen()
                }
            }
        }
    }
}

@Composable
fun CaloriesScreen() {
    var weightInput by remember { mutableStateOf("") }
    val weight = weightInput.toFloatOrNull() ?: 0.00f
    var intensity by remember { mutableFloatStateOf(1.3f) }
    var calories by remember { mutableIntStateOf(0) }

    val radioOptions = listOf("Male", "Female")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Card (
        shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp),
        modifier = Modifier.paddingFromBaseline(top = 0.dp, bottom = 16.dp)
    ) {
        Heading(
            text = "Calories Calculator",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        )
        WeightField(
            weightInput = weightInput,
            onValueChange = { weightInput = it.replace(',', '.') },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        GenderChoices(
            radioOptions = radioOptions,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected,
        )
        IntensityList(
            onClick = { intensity = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Calories: ",
            )
            Text(
                text = calories.toString(),
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            )
        }
        CaloriesCalculator(
            gender = selectedOption == radioOptions[0],
            weight = weight,
            intensity = intensity,
            setCalories = { calories = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CaloriesPreview() {
    CaloriesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CaloriesScreen()
        }
    }
}