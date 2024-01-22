package com.example.app.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.bmi.ui.theme.BMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi() {
    var heightInput: String by remember {
        mutableStateOf("")
    }
    var weightInput: String by remember {
        mutableStateOf("")
    }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi =
        if (weight > 0 && height > 0) weight / (height * height) else 0.0

    Column (
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 400.dp, height = 400.dp)
                .padding(horizontal = 26.dp, vertical = 8.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment =  Alignment.CenterHorizontally,
                modifier = Modifier
                    //.fillMaxHeight()
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_bmi),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = "Calculate your body mass index by simply entering your height and weight.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
        Card (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                //.size(width = 400.dp, height = 400.dp)
                .padding(horizontal = 26.dp, vertical = 8.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp)
            ) {
                Text(
                    text = stringResource(R.string.bmi_result),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = String.format("%.2f", bmi),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Card (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 400.dp, height = 400.dp)
                .padding(horizontal = 26.dp, vertical = 8.dp)
        ) {
            Column (
                modifier = Modifier
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = heightInput,
                    onValueChange = { heightInput = it.replace(',', '.') },
                    label = { Text("Height") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it.replace(',', '.') },
                    label = { Text("Weight") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it.replace(',', '.') },
                    label = { Text("Age") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it.replace(',', '.') },
                    label = { Text("Address") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}

@Preview
@Composable
fun BmiPreview() {
    BMITheme {
        Bmi()
    }
}
