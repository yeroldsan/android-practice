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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
    val height = heightInput.toFloatOrNull() ?: 0.00F
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi =
        if (weight > 0 && height > 0) weight / (height * height) else 0.00F

    Column (
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(36.dp)
    ) {
        Introduction()
        Result(bmi = bmi)
        DataInput(
            heightInput,
            weightInput,
            onHeightChange = { heightInput = it },
            onWeightChange = { weightInput = it }
        )
    }
}

@Composable
fun Introduction() {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            //.size(width = 400.dp, height = 400.dp)
            .padding(vertical = 8.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.title_bmi),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            Text(
                text = stringResource(R.string.intro),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun Result(bmi: Float) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
                //else if (bmi < 18.5F) MaterialTheme.colorScheme.secondary
                //else if (bmi < 25F) MaterialTheme.colorScheme.primary
                //else MaterialTheme.colorScheme.error
        ),
        modifier = Modifier
            .padding(vertical = 10.dp)
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
}

@Composable
fun DataInput(
    heightInput: String,
    weightInput: String,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 400.dp, height = 400.dp)
            .padding(vertical = 10.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
            )
            OutlinedTextField(
                value = heightInput,
                onValueChange = { onHeightChange(it.replace(',', '.')) },
                label = { Text("Height") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
            OutlinedTextField(
                value = weightInput,
                onValueChange = { onWeightChange(it.replace(',', '.')) },
                label = { Text("Weight") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
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
