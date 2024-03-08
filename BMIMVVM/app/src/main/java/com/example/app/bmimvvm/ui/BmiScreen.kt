package com.example.app.bmimvvm.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.app.bmimvvm.R

@Composable
fun BmiScreen(
    height: String,
    weight: String,
    bmiResult: Float,
    setHeight: (String) -> Unit,
    setWeight: (String) -> Unit,
    calculateResult: () -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(36.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Introduction()
        BmiResult(bmiResult)
        DataInput(height, weight, setHeight, setWeight, calculateResult)
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
                text = stringResource(R.string.bmi_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            Text(
                text = stringResource(R.string.introduction_message),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun BmiResult(bmiResult: Float) {
    Card (
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Text(
                text = "BMI",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = String.format("%.2f", bmiResult),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
                if (bmiResult == 0.0f) {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Face Icon")
                } else if (bmiResult < 18.0f) {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = "Underweight Icon")
                } else if (bmiResult < 26.0f) {
                    Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Normal Icon")
                } else {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Overweight Icon")
                }
            }
        }
    }
}

@Composable
fun DataInput(
    height: String,
    weight: String,
    setHeight: (String) -> Unit,
    setWeight: (String) -> Unit,
    calculateResult: () -> Unit
) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 400.dp, height = 400.dp)
            .padding(vertical = 10.dp)
            .imePadding()
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Outlined.Face,
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(65.dp)
            )
            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            OutlinedTextField(
                value = height,
                onValueChange = { setHeight(it.replace(',', '.')) },
                label = { Text("Height") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Person, contentDescription = "Email Icon")
                }
                )
            OutlinedTextField(
                value = weight,
                onValueChange = { setWeight(it.replace(',', '.')) },
                label = { Text("Weight") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Create, contentDescription = "Email Icon")
                }
            )
            Button(
                onClick = { calculateResult() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)

            ) {
                Text(text = "Calculate")
            }
        }
    }
}
