package com.example.app.bmimvvm.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.app.bmimvvm.viewmodel.BmiViewModel

@Composable
fun BmiScreen(
    height: String,
    weight: String,
    bmiResult: Float,
    setHeight: (String) -> Unit,
    setWeight: (String) -> Unit,
    calculateResult: () -> Unit
) {

    Column (
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(36.dp)
    ) {
        OutlinedTextField(
            value = height,
            onValueChange = { setHeight(it.replace(",", ".")) },
            label = { Text("Height (cm)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        OutlinedTextField(
            value = weight,
            onValueChange = { setWeight(it.replace(",", ".")) },
            label = { Text("Weight (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = String.format("BMI = %.2f", bmiResult),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Button(
            onClick = { calculateResult() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Calculate")
        }


//        Introduction()
//        BmiResult(bmiResult)
//        DataInput()
    }
}

@Composable
fun Introduction() {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "BMI Calculator",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "This app will calculate your BMI based on your height and weight using the metric system and the MVVM pattern.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun BmiResult(result: Float) {
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
                text = "BMI",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = String.format("%.2f", result),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun DataInput(
    heightInput: String = "0",
    weightInput: String = "0",
    onHeightChange: (String) -> Unit = {},
    onWeightChange: (String) -> Unit = {},
    calculateResult: () -> Unit = {}
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
                modifier = Modifier
                    .size(75.dp)
            )
            OutlinedTextField(
                value = heightInput,
                onValueChange = { onHeightChange(it.replace(',', '.')); calculateResult() },
                label = { Text("Height") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
            OutlinedTextField(
                value = weightInput,
                onValueChange = { onWeightChange(it.replace(',', '.')); calculateResult() },
                label = { Text("Weight") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}
