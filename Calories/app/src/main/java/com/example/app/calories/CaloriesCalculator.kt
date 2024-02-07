package com.example.app.calories

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CaloriesCalculator(
    gender: Boolean,
    weight: Float, intensity: Float,
    setCalories: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        elevation = ButtonDefaults.buttonElevation(8.dp),
        onClick = {
            if (gender) {
                setCalories(((879 + 10.2 * weight) * intensity).toInt())
            } else {
                setCalories(((795 + 7.18 * weight) * intensity).toInt())
            }
        },
        modifier = modifier
    ) {
        Text(text = "Calculate")
    }
}