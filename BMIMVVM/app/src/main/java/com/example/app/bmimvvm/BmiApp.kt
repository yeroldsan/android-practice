package com.example.app.bmimvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.app.bmimvvm.ui.BmiScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.bmimvvm.viewmodel.BmiViewModel

@Composable
fun BmiApp(viewModel: BmiViewModel = viewModel()) {
    val height by viewModel.height.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val bmiResult by viewModel.result.collectAsState()

    BmiScreen(
        height = height,
        weight = weight,
        bmiResult = bmiResult,
        setHeight = viewModel::setHeight,
        setWeight = viewModel::setWeight,
        calculateResult = viewModel::calculateBmi
    )
}